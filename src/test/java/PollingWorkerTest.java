import Task4.PollingWorker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PollingWorkerTest {

    private PollingWorker pollingWorker;

    @Test
    void stop_ShouldStopTheFlowCorrectly() throws InterruptedException {
        pollingWorker = new PollingWorker();

        Thread.sleep(2000);
        pollingWorker.stop();

        assertFalse(pollingWorker.getThread().isAlive());
    }
}
