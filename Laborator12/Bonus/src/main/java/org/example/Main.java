package org.example;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.*;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <source_directory>");
            System.exit(1);
        }

        String sourceDirectory = args[0];
        compileAndAnalyze(sourceDirectory);
    }

    private static void compileAndAnalyze(String sourceDirectory) {
        try {
            String outputDirectory = "D:\\OneDrive\\Documente\\GitHub\\Programare-Avansata\\Laborator12\\Bonus\\out";
            compileJavaFiles(sourceDirectory, outputDirectory);
            analyzeCompiledClasses(outputDirectory);

            System.out.println("Compilation and analysis successful.");
        } catch (IOException e) {
            System.err.println("Compilation and analysis failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void compileJavaFiles(String sourceDirectory, String outputDirectory) throws IOException {
        File sourceDir = new File(sourceDirectory);
        File[] files = sourceDir.listFiles((dir, name) -> name.endsWith(".java"));
        if (files != null) {
            for (File file : files) {
                Process compileProcess = Runtime.getRuntime().exec("javac -d " + outputDirectory + " " + file.getAbsolutePath());
                try {
                    compileProcess.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void analyzeCompiledClasses(String inputDirectory) throws IOException {
        File outputDir = new File(inputDirectory);
        File[] files = Objects.requireNonNull(outputDir.listFiles((dir, name) -> name.endsWith(".class")));
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            ClassReader cr = new ClassReader(fis);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassVisitor cv = new MyClass(cw);
            cr.accept(cv, ClassReader.SKIP_DEBUG);
            byte[] modifiedClass = cw.toByteArray();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(modifiedClass);
            }
            fis.close();
        }
    }
}