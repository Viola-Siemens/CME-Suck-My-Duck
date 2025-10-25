package com.hexagram2021.cme_suck_my_duck.transformers;

import com.hexagram2021.cme_suck_my_duck.CMESuckMyDuck;
import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class InjectLogTransformer implements ClassFileTransformer {
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
				reader.accept(new ClassVisitor(CMESuckMyDuck.ASM_API_VERSION, writer) {
					@Override
					public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
						Containers.logger.debug("Visit method " + name + ".");
						MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
						if (name.equals(InjectLogTransformer.this.methodName) || (name + descriptor).equals(InjectLogTransformer.this.methodName)) {
							Containers.logger.info("Found injection point in method %s.", name + descriptor);
							return new MethodVisitor(CMESuckMyDuck.ASM_API_VERSION, mv) {
								@Override
								public void visitCode() {
									super.visitCode();
									Containers.logger.info("Injecting...");
									this.visitMethodInsn(Opcodes.INVOKESTATIC, "com/hexagram2021/cme_suck_my_duck/utils/TraceIdGenerator", "getGlobalTraceId", "()Ljava/lang/String;", false);
									this.visitLdcInsn("Trace");
									this.visitMethodInsn(Opcodes.INVOKESTATIC, "com/hexagram2021/cme_suck_my_duck/utils/TraceLogger", "info", "(Ljava/lang/String;Ljava/lang/String;)V", false);
									Containers.logger.info("Injected.");
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
