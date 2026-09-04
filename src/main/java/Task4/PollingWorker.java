package Task4;

/**
 * Graceful stop через volatile. Класс PollingWorker запускает фоновый поток,
 * который в цикле делает работу(тик каждые 100 мс и принт номера тика), и метод stop() для корректной остановки
 */
public class PollingWorker {

    private final Thread thread;
    private volatile boolean isStopped = false;

    public PollingWorker(){
        this.thread = new Thread(() -> {
            int tickNumber = 0;
            while(!this.isStopped) {
                try {
                    tickNumber++;
                    Thread.sleep(100);
                    System.out.println(tickNumber);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        this.thread.start();
    }

    public void stop(){
        this.isStopped = true;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Thread getThread() {
        return thread;
    }
}
