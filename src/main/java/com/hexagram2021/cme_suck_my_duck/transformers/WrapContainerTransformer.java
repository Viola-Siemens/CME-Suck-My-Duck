package com.hexagram2021.cme_suck_my_duck.transformers;

import com.hexagram2021.cme_suck_my_duck.CMESuckMyDuck;
import com.hexagram2021.cme_suck_my_duck.Type;
import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import org.objectweb.asm.*;

import javax.annotation.Nullable;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class WrapContainerTransformer implements ClassFileTransformer {
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
				reader.accept(new ClassVisitor(CMESuckMyDuck.ASM_API_VERSION, writer) {
					@Override
					public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
						Containers.logger.debug("Visit method " + name + ".");
						MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

						if (name.equals("<clinit>")) {
							if (WrapContainerTransformer.this.phase == Phase.STATIC) {
								Containers.logger.info("Found injection point in method <clinit>.");
								return new MethodVisitor(CMESuckMyDuck.ASM_API_VERSION, mv) {
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
								return new MethodVisitor(CMESuckMyDuck.ASM_API_VERSION, mv) {
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

					@Override
					@Nullable
					public FieldVisitor visitField(int access, String name, String descriptor, @Nullable String signature, @Nullable Object value) {
						Containers.logger.debug("Visit field " + name + ".");

						if (name.equals(WrapContainerTransformer.this.fieldName)) {
							boolean isStatic = ((access & Opcodes.ACC_STATIC) != 0);
							if (WrapContainerTransformer.this.phase.isStatic() != isStatic) {
								Containers.logger.fatal(
										"Failed to verify phase. Expected: %s. Found: %s.",
										WrapContainerTransformer.this.phase.getPhaseName(),
										isStatic ? "static" : "nonstatic"
								);
							} else {
								if (!descriptor.equals(WrapContainerTransformer.this.type.getTypeFullClassName())) {
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
			} catch (Exception e) {
				Containers.logger.error(e);
			}
		}
		return classFileBuffer;
	}
}
