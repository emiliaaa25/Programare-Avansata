import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.regex.Pattern;

import org.junit.Test;

public class ClassAnalyzer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ClassAnalyzer <class_name_or_directory_or_jar>");
            return;
        }

        String input = args[0];

        File file = new File(input);
        List<URL> urls = new ArrayList<>();

        if (file.isDirectory()) {
            List<String> classNames = new ArrayList<>();
            findClassFiles(file, classNames, file.getAbsolutePath(), urls);
            processClasses(classNames, urls);
        } else if (file.isFile() && input.endsWith(".jar")) {
            List<String> classNames = extractClassesFromJar(file);
            processClasses(classNames, urls);
        } else {
            processClass(input, null);
        }
    }

    private static void findClassFiles(File directory, List<String> classNames, String rootPath, List<URL> urls) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findClassFiles(file, classNames, rootPath, urls);
                } else if (file.getName().endsWith(".class")) {
                    String className = convertFilePathToClassName(file, rootPath);
                    classNames.add(className);
                }
            }
            try {
                urls.add(directory.toURI().toURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String convertFilePathToClassName(File file, String rootPath) {
        String path = file.getAbsolutePath();
        path = path.substring(rootPath.length() + 1).replace(File.separatorChar, '.');
        path = path.substring(0, path.length() - 6);  // Remove ".class"
        return path;
    }

    private static List<String> extractClassesFromJar(File jarFile) {
        List<String> classNames = new ArrayList<>();
        try (JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarFile))) {
            JarEntry entry;
            while ((entry = jarInputStream.getNextJarEntry()) != null) {
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace("/", ".").replace(".class", "");
                    classNames.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNames;
    }

    private static void processClasses(List<String> classNames, List<URL> urls) {
        try (URLClassLoader urlClassLoader = new URLClassLoader(urls.toArray(new URL[0]))) {
            for (String className : classNames) {
                processClass(className, urlClassLoader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processClass(String className, URLClassLoader classLoader) {
        try {
            Class<?> cls;
            if (classLoader != null) {
                cls = classLoader.loadClass(className);
            } else {
                cls = Class.forName(className);
            }

            Method[] methods = cls.getDeclaredMethods();
            Map<String, Integer> testStats = new HashMap<>();

            for (Method method : methods) {
                if (!method.isAnnotationPresent(Test.class) && method.getModifiers() == Modifier.PUBLIC) {
                    System.out.println("Invoking method: " + method.getName());
                    Object[] args = generateMockArgs(method.getParameterTypes());
                    try {
                        method.invoke(cls.getDeclaredConstructor().newInstance(), args);
                        testStats.put(method.getName(), 1);
                    } catch (Exception e) {
                        testStats.put(method.getName(), 0);
                        e.printStackTrace();
                    }
                }
            }

            if (!testStats.isEmpty()) {
                System.out.println("Class: " + className);
                printTestStatistics(testStats);
            } else {
                System.out.println("Class does not contain any public test methods: " + className);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        }
    }


    private static Object[] generateMockArgs(Class<?>[] parameterTypes) {
        List<Object> args = new ArrayList<>();
        for (Class<?> paramType : parameterTypes) {
            if (paramType.isPrimitive()) {
                args.add(generateMockPrimitive(paramType));
            } else if (paramType == String.class) {
                args.add("MockString");
            } else {
                args.add(null);
            }
        }
        return args.toArray(new Object[0]);
    }


    private static Object generateMockPrimitive(Class<?> type) {
        if (type == boolean.class) {
            return false;
        } else if (type == byte.class) {
            return (byte) 0;
        } else if (type == char.class) {
            return '\u0000';
        } else if (type == short.class) {
            return (short) 0;
        } else if (type == int.class) {
            return 0;
        } else if (type == long.class) {
            return 0L;
        } else if (type == float.class) {
            return 0.0f;
        } else if (type == double.class) {
            return 0.0;
        } else {
            return null;
        }
    }

    private static void printTestStatistics(Map<String, Integer> testStats) {
        int totalTests = testStats.size();
        int passedTests = (int) testStats.values().stream().filter(val -> val == 1).count();
        int failedTests = totalTests - passedTests;

        System.out.println("Test Statistics:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + failedTests);
    }
}
