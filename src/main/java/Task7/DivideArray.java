package Task7;

import java.util.concurrent.*;

/**
 * Раздели массив на K частей, K потоков считают частичную сумму своей части, на барьере сводят в общий итог, печатают;
 * для демонстрации переиспользования повтори на втором массиве
 */
public class DivideArray {

    public void process(int [] array, int partiesCount, CyclicBarrier cyclicBarrier, int[] sumsParties) throws InterruptedException {
        int partSize = array.length/partiesCount;
        ExecutorService executorService = Executors.newFixedThreadPool(partiesCount);
        for (int i = 0; i < partiesCount; i++) {
            int finalI = i;
            executorService.submit(() -> {
                int start = finalI * partSize;
                int end = (finalI == partiesCount - 1) ? array.length : start + partSize;
                sumsParties[finalI] = sumOfPart(array, start, end);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();
        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }
    }

    private int sumOfPart(int [] array, int start, int end){
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }
}
