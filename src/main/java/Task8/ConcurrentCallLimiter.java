package Task8;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Доработай ограничитель из задачи 3: если за 200 мс слот не освободился - не ждать дальше,
 * а вернуть быстрый отказ (как 503), не уходя в бесконечное ожидание
 */
public class ConcurrentCallLimiter {

    private final Semaphore semaphore;

    public ConcurrentCallLimiter(int limit) {
        this.semaphore = new Semaphore(limit);
    }

    public void apply() throws InterruptedException {
        try {
            if(!semaphore.tryAcquire(200, TimeUnit.MILLISECONDS)){
                throw new RejectedExecutionException("Rejected");
            }
        } finally {
            this.semaphore.release();
        }
    }
}
