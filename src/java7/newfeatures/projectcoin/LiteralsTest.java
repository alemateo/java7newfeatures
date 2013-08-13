package java7.newfeatures.projectcoin;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Examples of new literals added in java7
 */
public class LiteralsTest {

    /**
     *All numeric values can have underscore between numbers to be more readable
     */
    @Test
    public void testLiteralWithUndercore() {
        int oneMillon = 1000000;
        assertEquals(oneMillon, 1_000_000);

        double pi = 3.1416;
        assertEquals(pi, 3.14_16);
    }

    /**
     * now you can use the 0b or 0B to write byte,short, int and long values in binary numeric system
     */
    @Test
    public void testBinaryNumbers() {
        byte eight = 0b1000;
        assertEquals(8, eight);

        //allinone
        int num130 = 0B1000_0010;
        assertEquals(130, num130);
    }
}
