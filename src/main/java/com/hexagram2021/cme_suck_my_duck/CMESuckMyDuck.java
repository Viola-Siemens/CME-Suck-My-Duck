package com.hexagram2021.cme_suck_my_duck;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import com.hexagram2021.cme_suck_my_duck.utils.SharedConstants;
import org.objectweb.asm.*;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.nio.file.StandardOpenOption;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.hexagram2021.cme_suck_my_duck.utils.SharedConstants.LOG_PATH;

public class CMESuckMyDuck {
	public static final Gson GSON = new Gson();
	public static final Log logger = new Log(LOG_PATH, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
	public static final int ASM_API_VERSION;
	public static final boolean INJECT_METHOD;

	public static void main(String[] args) {
		System.out.println("This project can only be used as javaagent.");
		System.out.println("Usage:");
		System.out.println("\t-javaagent:CMESuckMyDuck-<version>.jar=<class full name>;<field name>;<type>;<phase>");
		System.out.println("For example:");
		System.out.println("\t-javaagent:CMESuckMyDuck-" + SharedConstants.VERSION + ".jar=org/violetmoon/zetaimplforge/event/ForgeZetaEventBus;convertedHandlers;Map;nonstatic");
		System.out.println("Which means, each modification of map `convertedHandlers`, which is a nonstatic member in `ForgeZetaEventBus`, will be traced - when add and remove is called, a stacktrace will be printed to the log.");
		System.out.println("All valid types:");
		for(Type type: Type.values()) {
			System.out.printf(" -\t%s\n", type.getTypeName());
		}
		System.out.println("All valid phases:");
		for(Phase phase: Phase.values()) {
			System.out.printf(" -\t%s\n", phase.getPhaseName());
		}
	}

	public static void premain(String agentArg, Instrumentation inst) {
		//Pre-Load
		try (InputStream is = CMESuckMyDuck.class.getResourceAsStream("/meta.json")) {
			String version = GSON.fromJson(new InputStreamReader(Objects.requireNonNull(is)), JsonObject.class).get("version").getAsString();
			logger.info(String.format("CMESuckMyDuck v%s", version));
		} catch (Exception e) {
			logger.error("Error getting version of CMESuckMyDuck.");
			logger.error(e);
		}

		String[] args = agentArg.split(";");
		if(INJECT_METHOD) {
			if (args.length < 2) {
				logger.error("Failed to parse agent arguments. Expect 2 arguments, found %d: [%s].", args.length, Log.buildArrayString(args));
				return;
			}
			inst.addTransformer(new InjectLogTransformer(args[0], args[1]), true);
			logger.info("Successfully added transformer for method %s of class %s.", args[1], args[0]);
		} else {
			//Parse
			if (args.length < 4) {
				logger.error("Failed to parse agent arguments. Expect 4 arguments, found %d: [%s].", args.length, Log.buildArrayString(args));
				return;
			}
			//Main
			inst.addTransformer(new WrapContainerTransformer(args[0], args[1], Type.fromName(args[2]), Phase.fromName(args[3])), true);
			logger.info("Successfully added transformer for field %s of class %s, type %s, phase %s.", args[1], args[0], args[2], args[3]);
		}
	}

	static class WrapContainerTransformer implements ClassFileTransformer {
		final String className;
		final String fieldName;
		final Type type;
		final Phase phase;

		public WrapContainerTransformer(String className, String fieldName, Type type, Phase phase) {
			this.className = className;
			this.fieldName = fieldName;
			this.type = type;
			this.phase = phase;
		}

		@Override
		public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
			if (className.equals(this.className)) {
				try {
					Containers.logger.info("Found class " + this.className + ".");
					ClassReader reader = new ClassReader(classFileBuffer);
					ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
					reader.accept(new ClassVisitor(ASM_API_VERSION, writer) {
						@Override
						public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
							Containers.logger.debug("Visit method " + name + ".");
							MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

							if (name.equals("<clinit>")) {
								if (WrapContainerTransformer.this.phase == Phase.STATIC) {
									Containers.logger.info("Found injection point in method <clinit>.");
									return new MethodVisitor(ASM_API_VERSION, mv) {
										@Override
										public void visitInsn(int opcode) {
											if (opcode == Opcodes.RETURN) {
												Containers.logger.info("Injecting...");
												this.visitFieldInsn(Opcodes.GETSTATIC, "com/hexagram2021/cme_suck_my_duck/Type", WrapContainerTransformer.this.type.name(), "Lcom/hexagram2021/cme_suck_my_duck/Type;");
												this.visitFieldInsn(Opcodes.GETSTATIC, WrapContainerTransformer.this.className, WrapContainerTransformer.this.fieldName, WrapContainerTransformer.this.type.getTypeFullClassName());
												this.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/hexagram2021/cme_suck_my_duck/Type", "construct", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
												this.visitFieldInsn(Opcodes.PUTSTATIC, WrapContainerTransformer.this.className, WrapContainerTransformer.this.fieldName, WrapContainerTransformer.this.type.getTypeFullClassName());
												Containers.logger.info("Injected.");
											}
											super.visitInsn(opcode);
										}
									};
								}
							} else if (name.equals("<init>")) {
								if (WrapContainerTransformer.this.phase == Phase.NONSTATIC) {
									Containers.logger.info("Found injection point in method <init>.");
									return new MethodVisitor(ASM_API_VERSION, mv) {
										@Override
										public void visitInsn(int opcode) {
											if (opcode == Opcodes.RETURN) {
												Containers.logger.info("Injecting...");
												this.visitVarInsn(Opcodes.ALOAD, 0);
												this.visitFieldInsn(Opcodes.GETSTATIC, "com/hexagram2021/cme_suck_my_duck/Type", WrapContainerTransformer.this.type.name(), "Lcom/hexagram2021/cme_suck_my_duck/Type;");
												this.visitVarInsn(Opcodes.ALOAD, 0);
												this.visitFieldInsn(Opcodes.GETFIELD, WrapContainerTransformer.this.className, WrapContainerTransformer.this.fieldName, WrapContainerTransformer.this.type.getTypeFullClassName());
												this.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/hexagram2021/cme_suck_my_duck/Type", "construct", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
												this.visitFieldInsn(Opcodes.PUTFIELD, WrapContainerTransformer.this.className, WrapContainerTransformer.this.fieldName, WrapContainerTransformer.this.type.getTypeFullClassName());
												Containers.logger.info("Injected.");
											}
											super.visitInsn(opcode);
										}
									};
								}
							}
							return mv;
						}

						@Override @Nullable
						public FieldVisitor visitField(int access, String name, String descriptor, @Nullable String signature, @Nullable Object value) {
							Containers.logger.debug("Visit field " + name + ".");

							if(name.equals(WrapContainerTransformer.this.fieldName)) {
								boolean isStatic = ((access & Opcodes.ACC_STATIC) != 0);
								if(WrapContainerTransformer.this.phase.isStatic() != isStatic) {
									Containers.logger.fatal(
											"Failed to verify phase. Expected: %s. Found: %s.",
											WrapContainerTransformer.this.phase.getPhaseName(),
											isStatic ? "static" : "nonstatic"
									);
								} else {
									if(!descriptor.equals(WrapContainerTransformer.this.type.getTypeFullClassName())) {
										Containers.logger.fatal(
												"Failed to verify type. Expected: %s. Found: %s.",
												WrapContainerTransformer.this.type.getTypeFullClassName(),
												descriptor
										);
									}
								}
							}

							return super.visitField(access, name, descriptor, signature, value);
						}
					}, 0);
					return writer.toByteArray();
				} catch(Exception e) {
					Containers.logger.error(e);
				}
			}
			return classFileBuffer;
		}
	}

	static class InjectLogTransformer implements ClassFileTransformer {
		final String className;
		final String methodName;

		public InjectLogTransformer(String className, String methodName) {
			this.className = className;
			this.methodName = methodName;
		}

		@Override
		public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
			if (className.equals(this.className)) {
				try {
					Containers.logger.info("Found class " + this.className + ".");
					ClassReader reader = new ClassReader(classFileBuffer);
					ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
					reader.accept(new ClassVisitor(ASM_API_VERSION, writer) {
						@Override
						public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
							Containers.logger.debug("Visit method " + name + ".");
							MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
							if(name.equals(InjectLogTransformer.this.methodName)) {
								Containers.logger.info("Found injection point in method %s.", InjectLogTransformer.this.methodName);
								return new MethodVisitor(ASM_API_VERSION, mv) {
									@Override
									public void visitCode() {
										super.visitCode();
										Containers.logger.info("Injecting...");
										this.visitFieldInsn(Opcodes.GETSTATIC, "com/hexagram2021/cme_suck_my_duck/containers/Containers", "logger", "Lcom/hexagram2021/cme_suck_my_duck/utils/Log;");
										this.visitTypeInsn(Opcodes.NEW, "com/hexagram2021/cme_suck_my_duck/utils/SuckTraceException");
										this.visitInsn(Opcodes.DUP);
										this.visitLdcInsn("Trace");
										this.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/hexagram2021/cme_suck_my_duck/utils/SuckTraceException", "<init>", "(Ljava/lang/String;)V", false);
										this.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/hexagram2021/cme_suck_my_duck/utils/Log", "info", "(Ljava/lang/Throwable;)V", false);
										Containers.logger.info("Injected.");
									}
								};
							}
							return mv;
						}
					}, 0);
					return writer.toByteArray();
				} catch(Exception e) {
					Containers.logger.error(e);
				}
			}
			return classFileBuffer;
		}
	}

	enum Phase {
		STATIC("static"), NONSTATIC("nonstatic");

		private static final Map<String, Phase> BY_NAME;
		private final String phaseName;

		Phase(String phaseName) {
			this.phaseName = phaseName;
		}

		public String getPhaseName() {
			return this.phaseName;
		}

		public static Phase fromName(String phaseName) {
			Phase ret = BY_NAME.get(phaseName);
			if(ret == null) {
				throw new IllegalArgumentException(String.format("No phase named %s!", phaseName));
			}
			return ret;
		}

		public boolean isStatic() {
			return this == STATIC;
		}

		static {
			BY_NAME = new HashMap<>();
			for(Phase phase: values()) {
				BY_NAME.put(phase.getPhaseName(), phase);
			}
		}
	}

	static {
		int asmApiVersion = Opcodes.ASM9;
		try {
			int level = Integer.parseInt(System.getProperty("cme_suck_my_duck.asm_api_version"));
			asmApiVersion = level << 16;
		} catch (Exception ignored) {
		}
		ASM_API_VERSION = asmApiVersion;
		boolean injectMethod = false;
		try {
			injectMethod = Boolean.parseBoolean(System.getProperty("cme_suck_my_duck.inject_method"));
		} catch (Exception ignored) {
		}
		INJECT_METHOD = injectMethod;
	}
}