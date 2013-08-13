package java7.newfeatures.projectcoin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * The try-with-resources statement is a try statement that declares one or more resources
 * and it ensures that each resource is closed at the end of the statement.
 * Any object that implements java.lang.AutoCloseable can be use as a resource.
 */
public class TryWithResourceTest {
    /**
     * Resource class auto closeable for testing
     */
    private static class MyResource implements AutoCloseable {
        private boolean closed = false;

        @Override
        public void close() {
            closed = true;
        }

        public boolean isClosed() {return closed;}
    }


    @Test
    public void testCloseOneResource(){
        MyResource ref;
        try(MyResource resource = new MyResource()) {
           ref = resource;
        }

        assertTrue(ref.isClosed());
    }


    @Test
    public void testCloseMultipleResources(){
        MyResource ref1;
        MyResource ref2;
        MyResource ref3;

        try(MyResource resource1 = new MyResource();
            MyResource resource2 = new MyResource();
            MyResource resource3 = new MyResource()) {

            ref1 = resource1;
            ref2 = resource2;
            ref3 = resource3;
        }

        assertTrue(ref1.isClosed());
        assertTrue(ref2.isClosed());
        assertTrue(ref3.isClosed());
    }


    /**
     * Exception and resource for testing suppressed exceptions in try-with-resource
     */
    private static class MyResourceException extends  Exception{}

    private static class MyResourceWithException implements AutoCloseable {
        private boolean closeInvoked = false;
        private MyResourceException exceptionThrowed;

        @Override
        public void close() throws MyResourceException {
            closeInvoked = true;
            exceptionThrowed = new MyResourceException();

            throw exceptionThrowed;
        }

        public boolean isCloseInvoked() {return closeInvoked;}
        public MyResourceException getExceptionThrowed(){return exceptionThrowed;}
    }

    @Test
    public void testSuppressedExceptionInTryResourceBlock() {
        MyResourceWithException ref = null;
        try {
            try(MyResourceWithException resource = new MyResourceWithException()) {
                ref = resource;
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();

            assertNotNull(ref);
            assertTrue(ref.isCloseInvoked());
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals(ref.getExceptionThrowed(), e.getSuppressed()[0]);
        }
    }


}
