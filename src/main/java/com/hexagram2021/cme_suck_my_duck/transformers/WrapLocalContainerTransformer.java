package com.hexagram2021.cme_suck_my_duck.transformers;

import com.hexagram2021.cme_suck_my_duck.CMESuckMyDuck;
import com.hexagram2021.cme_suck_my_duck.Type;
import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class WrapLocalContainerTransformer implements ClassFileTransformer {
	final String className;
	final String methodName;
	final Type type;
	final int localVarIndex;
	final int matchLocalIndex;

	public WrapLocalContainerTransformer(String className, String methodName, Type type, int localVarIndex, int matchLocalIndex) {
		this.className = className;
		this.methodName = methodName;
		this.type = type;
		this.localVarIndex = localVarIndex;
		this.matchLocalIndex = matchLocalIndex;
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

						if (name.equals(WrapLocalContainerTransformer.this.methodName) || (name + descriptor).equals(WrapLocalContainerTransformer.this.methodName)) {
							Containers.logger.info("Found injection point in method %s.", name + descriptor);
							return new MethodVisitor(CMESuckMyDuck.ASM_API_VERSION, mv) {
								int matchCount = 0;

								@Override
								public void visitVarInsn(int opcode, int varIndex) {
									super.visitVarInsn(opcode, varIndex);
									if (opcode == Opcodes.ASTORE && varIndex == WrapLocalContainerTransformer.this.localVarIndex) {
										if(WrapLocalContainerTransformer.this.matchLocalIndex == -1 || this.matchCount == WrapLocalContainerTransformer.this.matchLocalIndex) {
											Containers.logger.info("Injecting...");
											this.visitFieldInsn(Opcodes.GETSTATIC, "com/hexagram2021/cme_suck_my_duck/Type", WrapLocalContainerTransformer.this.type.name(), "Lcom/hexagram2021/cme_suck_my_duck/Type;");
											super.visitVarInsn(Opcodes.ALOAD, varIndex);
											this.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/hexagram2021/cme_suck_my_duck/Type", "construct", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
											super.visitVarInsn(Opcodes.ASTORE, varIndex);
											Containers.logger.info("Injected.");
										}
										this.matchCount += 1;
									}
								}
							};
						}
						return mv;
					}
				}, 0);
				byte[] bytes = writer.toByteArray();
				if (Containers.OUTPUT_CLASS_BINARY) {
					Containers.logger.info("Outputting class binary...");
					String[] split = className.split("/");
					try(FileOutputStream out = new FileOutputStream(split[split.length - 1] + ".class")) {
						out.write(bytes);
					} catch (IOException e) {
						Containers.logger.error("Failed to output binary class file.", e);
					}
				}
				return bytes;
			} catch (Exception e) {
				Containers.logger.error(e);
			}
		}
		return classFileBuffer;
	}
}
