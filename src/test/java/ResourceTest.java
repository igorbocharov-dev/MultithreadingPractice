import Task2.Resource;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceTest {

    @RepeatedTest(value = 100)
    void getResource_ShouldCreateOneInstanceAndGiveOnlyIt() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        int id = 1;

        Runnable task1 = (() -> Resource.getResource(id));
        Runnable task2 = (() -> Resource.getResource(id));
        Runnable task3 = (() -> Resource.getResource(id));
        Runnable task4 = (() -> Resource.getResource(id));

        Future<?> result1 = executorService.submit(task1);
        Future<?> result2 = executorService.submit(task2);
        Future<?> result3 = executorService.submit(task3);
        Future<?> result4 = executorService.submit(task4);

        executorService.shutdown();

        if(!executorService.awaitTermination(1, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }

        Resource instance1 = (Resource) result1.get();
        Resource instance2 = (Resource) result2.get();
        Resource instance3 = (Resource) result3.get();
        Resource instance4 = (Resource) result4.get();

        assertEquals(instance1, instance2);
        assertEquals(instance2, instance3);
        assertEquals(instance3, instance4);
    }
}
