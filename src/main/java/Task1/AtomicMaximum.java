package Task1;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Атомарный максимум. submit(long) из N потоков хранит максимум, getMax() отдаёт без блокировок
 */
public class AtomicMaximum {

    private final AtomicLong currentNum = new AtomicLong();

    public void submit(long count){
        this.currentNum.accumulateAndGet(count, Math::max);
    }

    public long getMax(){
        return this.currentNum.get();
    }
}

