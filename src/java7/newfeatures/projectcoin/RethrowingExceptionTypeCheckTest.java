package java7.newfeatures.projectcoin;

import org.junit.Test;

/**
 * The Java SE 7 compiler performs more precise analysis of rethrown exceptions than earlier releases of Java SE
 */
public class RethrowingExceptionTypeCheckTest {

    static class FirstException extends Exception { }
    static class SecondException extends Exception { }

    /**
     * This method do not compile in early releases.
     * A compiler from a release prior to Java SE 7 generates the error,
     * "unreported exception Exception; must be caught or declared to be thrown" at the statement throw e
     * @throws FirstException, SecondException
     */
    public void rethrowExceptionInJava7(String exceptionName) throws FirstException, SecondException {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception e) {
            throw e; //ERROR in older java
        }
    }


    /**
     * Same method but compatible with previous java versions
     * @throws Exception
     */
    public void rethrowExceptionInOlderJava(String exceptionName) throws Exception{
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * The type inference is lost when the exception is assigned to a variable
     * @param exceptionName
     * @throws Exception
     */
    public void rethrowExceptionInJava7Complex(String exceptionName) throws Exception {
        try {
            if (exceptionName.equals("First")) {
                throw new FirstException();
            } else {
                throw new SecondException();
            }
        } catch (Exception e) {
            Exception ex = e; //type inference is lost because of the assignment
            throw ex;
        }
    }

    /**
     * We can throw the specific exceptions
     * @throws SecondException
     * @throws FirstException
     */
    @Test(expected = FirstException.class)
    public void testRethrowWithJava7 () throws SecondException, FirstException {
       rethrowExceptionInJava7("First");
    }


    /**
     * We need to throw the generic Exception
     * @throws Exception
     */
    @Test(expected = SecondException.class)
    public void testRethrowExceptionInOlderJava () throws Exception {
        rethrowExceptionInOlderJava("Second");
    }

}
