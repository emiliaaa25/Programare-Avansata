import org.junit.Test;
import static org.junit.Assert.*;

public class SampleTest {

    @Test
    public void testAddition() {
        int a = 5;
        int b = 10;
        assertEquals(15, a + b);
    }

    @Test
    public void testSubtraction() {
        int a = 10;
        int b = 5;
        assertEquals(5, a - b);
    }

    @Test
    public void testMultiplication() {
        int a = 5;
        int b = 10;
        assertEquals(50, a * b);
    }

    @Test
    public void testDivision() {
        int a = 10;
        int b = 5;
        assertEquals(2, a / b);
    }

    @Test
    public void testFailingCase() {
        int a = 5;
        int b = 10;
        assertEquals(50, a * b);
    }

    @Test
    public void testEquality() {
        String str1 = "Hello";
        String str2 = "Hello";
        assertEquals(str1, str2);
    }

    @Test
    public void testArrayEquality() {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        assertArrayEquals(array1, array2);
    }

    @Test
    public void testNotNull() {
        String str = "NotNull";
        assertNotNull(str);
    }

    @Test
    public void testNull() {
        String str = null;
        assertNull(str);
    }

    @Test
    public void testGreaterThan() {
        int a = 10;
        int b = 5;
        assertTrue(a > b);
    }

    @Test
    public void testLessThan() {
        int a = 5;
        int b = 10;
        assertTrue(a < b);
    }
}
