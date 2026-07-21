import org.junit.jupiter.api.RepeatedTest;

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

        Thread thread1 = new Thread(() -> rangeClosed(0, count1).forEach(i -> atomicMaximum.submit(i)));
        Thread thread2 = new Thread(() -> rangeClosed(0, max).forEach(i -> atomicMaximum.submit(i)));
        Thread thread3 = new Thread(() -> rangeClosed(0, count2).forEach(i -> atomicMaximum.submit(i)));
        Thread thread4 = new Thread(() -> rangeClosed(0, count3).forEach(i -> atomicMaximum.submit(i)));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        assertEquals(max, atomicMaximum.getMax());
    }
}
