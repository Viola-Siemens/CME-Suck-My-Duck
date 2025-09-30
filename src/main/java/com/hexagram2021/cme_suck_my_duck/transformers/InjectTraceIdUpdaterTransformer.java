package com.hexagram2021.cme_suck_my_duck.transformers;

import com.hexagram2021.cme_suck_my_duck.CMESuckMyDuck;
import com.hexagram2021.cme_suck_my_duck.containers.Containers;
import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class InjectTraceIdUpdaterTransformer implements ClassFileTransformer {
	final String className;
	final String methodName;

	public InjectTraceIdUpdaterTransformer(String className, String methodName) {
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
						if (name.equals(InjectTraceIdUpdaterTransformer.this.methodName) || (name + descriptor).equals(InjectTraceIdUpdaterTransformer.this.methodName)) {
							Containers.logger.info("Found injection point in method %s.", name + descriptor);
							return new MethodVisitor(CMESuckMyDuck.ASM_API_VERSION, mv) {
								@Override
								public void visitCode() {
									super.visitCode();
									Containers.logger.info("Injecting...");
									this.visitMethodInsn(Opcodes.INVOKESTATIC, "com/hexagram2021/cme_suck_my_duck/utils/TraceIdGenerator", "updateTraceId", "()V", false);
									Containers.logger.info("Injected.");
								}
							};
						}
						return mv;
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
