import Task3.ConcurrentCallLimiter;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcurrentCallLimiterTest {

    private ConcurrentCallLimiter concurrentCallLimiter;

    @Test
    void apply_ShouldNotExceedLimitOnNumberOfThreads() throws InterruptedException {
        final int threadLimit = 10;
        concurrentCallLimiter = new ConcurrentCallLimiter(threadLimit);

        final int threadPoolCount = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolCount);
        for (int i = 0; i < threadPoolCount; i++) {
            executorService.submit(() -> {
                try {
                    concurrentCallLimiter.apply();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
            });
        }
        executorService.shutdown();

        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }

        int threadCount = concurrentCallLimiter.getThreadCount().get();

        assertTrue((threadCount <= threadLimit && threadCount >= 0));
    }
}
