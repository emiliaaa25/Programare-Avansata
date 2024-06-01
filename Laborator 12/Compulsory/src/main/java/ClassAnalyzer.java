import java.lang.reflect.Method;
import org.junit.Test;

public class ClassAnalyzer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ClassAnalyzer <full_class_name>");
            return;
        }

        String className = args[0];
        try {
            Class<?> cls = Class.forName(className);
            System.out.println("Methods of class " + className + ":");
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
            System.out.println("\nInvoking @Test annotated static methods:");
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    if (java.lang.reflect.Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0) {
                        System.out.println("Invoking method: " + method.getName());
                        method.invoke(null);
                    } else {
                        System.out.println("Skipping method (not static or has parameters): " + method.getName());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}