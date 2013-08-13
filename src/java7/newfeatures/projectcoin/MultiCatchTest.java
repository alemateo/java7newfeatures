package java7.newfeatures.projectcoin;

import org.junit.Test;

import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;

/**
 *Example of new multi catch feature
 */
public class MultiCatchTest {

    private static class AException extends Exception{}
    private static class BException extends Exception{}

    private Logger logger = Logger.getLogger("MultiCatchTest");

    /**
     * Method implemented in the old way
     */
    private String oldFashionedMethod(String param) {
        try{
            shouldThrowAException(param);
            shouldThrowBException(param);
        } catch (AException e) {
            logger.info("AException!!!");
            return "Exception";
        } catch (BException e) {
            logger.info("BException!!!");
            return "Exception";
        }
        return param;
    }

    /**
     * Method implemented with multicatch
     * Be careful now you can not distinct what exception was throw
     * you need to use instanceof and this is a bad practice.
     * If you need to know what is the exact exception then do not use multi catch
     */
    private String newMulticatchWayMethod(String param) {
        try{
            shouldThrowAException(param);
            shouldThrowBException(param);
        } catch (AException | BException e) {
            logger.info(" We don not know if is A or B Exception!!!");
            return "Exception";
        }
        return param;
    }

    private void shouldThrowAException(String param) throws AException{
        if (param != null && param.equals("A"))
            throw new AException();
    }

    private void shouldThrowBException(String param) throws BException{
        if (param != null && param.equals("B"))
            throw new BException();
    }

    @Test
    public void testOldFashionedCatch(){
       assertEquals("Exception", oldFashionedMethod("A"));
       assertEquals("Exception", oldFashionedMethod("B"));
       assertEquals("Other", oldFashionedMethod("Other"));
    }

    @Test
    public void testNewMulticatchWay(){
        assertEquals("Exception", newMulticatchWayMethod("A"));
        assertEquals("Exception", newMulticatchWayMethod("B"));
        assertEquals("Other", newMulticatchWayMethod("Other"));
    }
}
