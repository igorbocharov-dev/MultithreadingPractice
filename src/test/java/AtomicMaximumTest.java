import Task1.AtomicMaximum;
import org.junit.jupiter.api.RepeatedTest;
import support.ThreadWorker;

import static java.util.stream.LongStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtomicMaximumTest {

    private AtomicMaximum atomicMaximum;

    @RepeatedTest(value = 100)
    void submit_ShouldAccumulateMaximum() throws InterruptedException{
        atomicMaximum = new AtomicMaximum();
        long count1 = 500_000L;
        long count2 = 768_000L;
        long count3 = 345_000L;
        long max = 1_000_000L;

        Runnable task1 = (() -> rangeClosed(0, count1).forEach(i -> atomicMaximum.submit(i)));
        Runnable task2 = (() -> rangeClosed(0, max).forEach(i -> atomicMaximum.submit(i)));
        Runnable task3 = (() -> rangeClosed(0, count2).forEach(i -> atomicMaximum.submit(i)));
        Runnable task4 = (() -> rangeClosed(0, count3).forEach(i -> atomicMaximum.submit(i)));

        ThreadWorker.work(task1, task2, task3, task4);

        assertEquals(max, atomicMaximum.getMax());
    }
}
