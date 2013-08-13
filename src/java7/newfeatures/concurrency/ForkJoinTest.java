package java7.newfeatures.concurrency;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The Fork/Join Framework in Java 7 is designed for work that can be broken down into smaller tasks and the
 * results of those tasks combined to produce the final result.
 * In general, classes that use the Fork/Join Framework follow the following simple algorithm:
 * <code>
 *     // pseudocode
 *     Result solve(Problem problem) {
 *      if (problem.size < SEQUENTIAL_THRESHOLD)
 *          return solveSequentially(problem);
 *      else {
 *          Result left, right;
 *          INVOKE-IN-PARALLEL {
 *              left = solve(extractLeftHalf(problem));
 *              right = solve(extractRightHalf(problem));
 *          }
 *          return combine(left, right);
 *      }
 *    }
 * </code>
 * Ref: http://www.javacodegeeks.com/2013/02/java-7-forkjoin-framework-example.html
 */
public class ForkJoinTest {

    @Test
    public void testMaximumFinderComputedDirectly() {
        // create a random data set
        final int[] data = new int[] {4,2,3,1};

        // submit the task to the pool
        final ForkJoinPool pool = new ForkJoinPool();
        final MaximumFinder finder = new MaximumFinder(data);
        int max = pool.invoke(finder);

        assertEquals(4, max);

        assertTrue(finder.wasComputedDirectly());

    }


    @Test
    public void testMaximumFinderComputedWithFork() {
        // create a random data set
        final int[] data = new int[1000];
        final Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100);
        }
        data[500] = 100;

        // submit the task to the pool
        final ForkJoinPool pool = new ForkJoinPool();
        final MaximumFinder finder = new MaximumFinder(data);
        int max = pool.invoke(finder);

        assertEquals(100, max);
        assertFalse(finder.wasComputedDirectly());

    }
}
