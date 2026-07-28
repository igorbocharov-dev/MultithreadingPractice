package Task3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Ограничитель одновременных вызовов.
 * Метод apply() внутри секции (в методе) не больше N потоков, остальные ждут;
 * лимит освобождается даже при исключении
 */
public class ConcurrentCallLimiter {

    private final Semaphore semaphore;
    private final AtomicInteger threadCount = new AtomicInteger();

    public ConcurrentCallLimiter(int limit) {
        this.semaphore = new Semaphore(limit);
    }

    public void apply() throws InterruptedException {
        try {
            synchronized (this){
                this.semaphore.acquire();
                threadCount.incrementAndGet();
            }
        } finally {
            synchronized (this) {
                this.semaphore.release();
                threadCount.decrementAndGet();
            }
        }
    }

    public AtomicInteger getThreadCount() {
        return threadCount;
    }
}
