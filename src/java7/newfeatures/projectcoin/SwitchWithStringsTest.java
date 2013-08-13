package java7.newfeatures.projectcoin;


import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 *Example of the use of switch with strings
 */
public class SwitchWithStringsTest {

    private int toNumber(String value) {
        int val;
        switch (value) {
            case "ONE":
            case "one":
                val = 1; break;
            case "TWO":
            case "two":
                val = 2; break;
            case "THREE":
            case "three":
                val =3;break;
            default: val = 0;
        }
        return val;
    }

    @Test
    public void testOneInLowerCase() {
        assertEquals(1, toNumber("one"));
    }

    @Test
    public void testTwoInUpperCase() {
        assertEquals(2, toNumber("TWO"));
    }
}

