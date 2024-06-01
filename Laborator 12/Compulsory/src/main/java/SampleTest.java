import org.junit.Test;

public class SampleTest {

    @Test
    public static void testMethod1() {
        System.out.println("testMethod1 executed");
    }

    @Test
    public static void testMethod2() {
        System.out.println("testMethod2 executed");
    }

    @Test
    public void nonStaticTestMethod() {
        System.out.println("nonStaticTestMethod executed");
    }

    public static void normalMethod() {
        System.out.println("normalMethod executed");
    }
}