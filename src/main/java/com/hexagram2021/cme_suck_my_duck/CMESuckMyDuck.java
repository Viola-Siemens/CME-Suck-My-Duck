package com.hexagram2021.cme_suck_my_duck;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import org.objectweb.asm.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class CMESuckMyDuck {
	public static final Gson GSON = new Gson();

	public static void main(String[] args) {
		System.out.println("This project can only be used as javaagent.");
		System.out.println("Usage:");
		System.out.println("\t-javaagent:CMESuckMyDuck.jar=<class full name>;<field name>;<type>;<phase>");
		System.out.println("For example:");
		System.out.println("\t-javaagent:CMESuckMyDuck.jar=org.violetmoon.zetaimplforge.event.ForgeZetaEventBus;convertedHandlers;Map;nonstatic");
		System.out.println("Which means, each modification of map `ForgeZetaEventBus#convertedHandlers` will be traced - when add and remove is called, a stacktrace will be printed to the log.");
		System.out.println("All valid types:");
		for(Type type: Type.values()) {
			System.out.printf(" -\t%s%n", type.getTypeName());
		}
		System.out.println("All valid phases:");
		for(Phase phase: Phase.values()) {
			System.out.printf(" -\t%s%n", phase.getPhaseName());
		}
	}

	public static void premain(String agentArg, Instrumentation inst) {
		//Pre-Load
		try (InputStream is = CMESuckMyDuck.class.getResourceAsStream("/meta.json")) {
			String version = GSON.fromJson(new InputStreamReader(Objects.requireNonNull(is)), JsonObject.class).get("version").getAsString();
			Log.info(String.format("CMESuckMyDuck v%s", version));
		} catch (Exception e) {
			Log.error("Error getting version of CMESuckMyDuck.");
			Log.error(e);
		}
		//Parse
		String[] args = agentArg.split(";");
		if(args.length < 4) {
			Log.error("Failed to parse agent arguments. Expect 4 arguments, found %d: [%s]", args.length, Log.buildArrayString(args));
			return;
		}
		//Main
		inst.addTransformer(new DefineTransformer(args[0], args[1], Type.fromName(args[2]), Phase.fromName(args[3])), true);
	}

	record DefineTransformer(String className, String fieldName, Type type, Phase phase) implements ClassFileTransformer {
		@Override
		public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
			if (className.equals(this.className)) {
				ClassReader reader = new ClassReader(classFileBuffer);
				ClassWriter writer = new ClassWriter(reader, 0);
				String typeFullName = "Ljava/util/" + DefineTransformer.this.type.getTypeName();
				reader.accept(new ClassVisitor(Opcodes.ASM9, writer) {
					@Override
					public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
						MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

						if(name.equals("<clinit>")) {
							if(DefineTransformer.this.phase == Phase.STATIC) {
								return new MethodVisitor(Opcodes.ASM9, mv) {
									@Override
									public void visitInsn(int opcode) {
										if (opcode == Opcodes.RETURN) {
											this.visitFieldInsn(Opcodes.GETSTATIC, "com/hexagram2021/cme_suck_my_duck/CMESuckMyDuck$Type", DefineTransformer.this.type.name(), "Lcom/hexagram2021/cme_suck_my_duck/CMESuckMyDuck$Type");
											this.visitFieldInsn(Opcodes.GETSTATIC, DefineTransformer.this.className, DefineTransformer.this.fieldName, typeFullName);
											this.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/hexagram2021/cme_suck_my_duck/CMESuckMyDuck$Type", "construct", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
											this.visitFieldInsn(Opcodes.PUTSTATIC, DefineTransformer.this.className, DefineTransformer.this.fieldName, typeFullName);
										}
										super.visitInsn(opcode);
									}
								};
							}
						} else if(name.equals("<init>")) {
							if(DefineTransformer.this.phase == Phase.NONSTATIC) {
								return new MethodVisitor(Opcodes.ASM9, mv) {
									@Override
									public void visitInsn(int opcode) {
										if (opcode == Opcodes.RETURN) {
											this.visitFieldInsn(Opcodes.GETSTATIC, "com/hexagram2021/cme_suck_my_duck/CMESuckMyDuck$Type", DefineTransformer.this.type.name(), "Lcom/hexagram2021/cme_suck_my_duck/CMESuckMyDuck$Type");
											this.visitFieldInsn(Opcodes.GETFIELD, DefineTransformer.this.className, DefineTransformer.this.fieldName, typeFullName);
											this.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/hexagram2021/cme_suck_my_duck/CMESuckMyDuck$Type", "construct", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
											this.visitFieldInsn(Opcodes.PUTFIELD, DefineTransformer.this.className, DefineTransformer.this.fieldName, typeFullName);
										}
										super.visitInsn(opcode);
									}
								};
							}
						}
						return mv;
					}
				}, 0);
			}
			return classFileBuffer;
		}
	}

	enum Type {
		LIST("List", Containers::newWrappedList);//, SET("Set", Containers::newWrappedSet), MAP("Map", Containers::newWrappedMap);

		private static final Map<String, Type> BY_NAME;
		private final String typeName;
		private final Function<Object, Object> constructor;

		Type(String typeName, Function<Object, Object> constructor) {
			this.typeName = typeName;
			this.constructor = constructor;
		}

		public String getTypeName() {
			return this.typeName;
		}

		@SuppressWarnings("unused")
		public Object construct(Object wrapped) {
			return this.constructor.apply(wrapped);
		}

		public static Type fromName(String typeName) {
			Type ret = BY_NAME.get(typeName);
			if(ret == null) {
				Log.fatal(String.format("No type named %s!", typeName));
				return LIST;
			}
			return ret;
		}

		static {
			BY_NAME = new HashMap<>();
			for(Type type: values()) {
				BY_NAME.put(type.getTypeName(), type);
			}
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
				Log.fatal(String.format("No phase named %s!", phaseName));
				return STATIC;
			}
			return ret;
		}

		static {
			BY_NAME = new HashMap<>();
			for(Phase phase: values()) {
				BY_NAME.put(phase.getPhaseName(), phase);
			}
		}
	}
}