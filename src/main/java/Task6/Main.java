package Task6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue queue = new SimpleMessageQueue(10);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);

        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(producer1);
        executorService.submit(producer2);

        executorService.submit(consumer1);
        executorService.submit(consumer2);
        executorService.submit(consumer3);

        executorService.shutdown();

        if(!executorService.awaitTermination(5, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }

        producer1.stop();
        producer2.stop();
    }
}
