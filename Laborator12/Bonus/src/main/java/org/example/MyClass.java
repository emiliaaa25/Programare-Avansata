package org.example;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClass extends ClassVisitor {
    public MyClass(ClassVisitor cv) {
        super(Opcodes.ASM8, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("Visiting method: " + name);
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        return new MyMethod(mv);
    }
}
