package java7.newfeatures.projectcoin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * You can replace the type arguments required to invoke the constructor of a generic class with an empty set of type
 * parameters (<>) as long as the compiler can infer the type arguments from the context.
 * This pair of angle brackets is informally called the diamond.
 *
 */

public class GenericTypeInferenceTest {

    @Test
    public void testStutterer() {
        List<String> names = new ArrayList<String>();

        names.add("Juan");
        names.addAll(new ArrayList<String>());

        assertTrue(names.size() == 1);
    }


    @Test
    public void testGenericVariableDeclarationTypeInference() {
        List<String> names = new ArrayList<>();

        names.add("Juan");

        //The following statement should fail since addAll expects Collection<? extends String>
        //names.addAll(new ArrayList<>());
        names.addAll(new ArrayList<String>()); //In this case you need to specify the type

        assertTrue(names.size() == 1);
    }


    /**
     * Non generic class with a generic constructor
     */
    private static class GenericConstructor {
        <T>GenericConstructor(T value) {}
    }

    @Test
    public void testGenericConstructorTypeInference() {
        //You do not need to specify the type. It inferes that is a String
        GenericConstructor myClass = new GenericConstructor("");
    }


    /**
     * Non generic class with a generic method
     */
    private static class Identity {
        <T> T apply(T value) {return value;}
    }

    @Test
    public void testGenericMethodTypeInference() {
        Identity identity = new Identity();

        //Not need to set the type
        long longValue = identity.apply(1L);
        assertEquals(1L, longValue);

        String stringValue = identity.apply("Yes");
        assertEquals("Yes", stringValue);

    }

}
