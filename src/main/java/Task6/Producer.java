package Task6;

public class Producer implements Runnable{

    private final MessageQueue queue;
    private volatile boolean isStopped;

    public Producer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int value = 0;
            while (!isStopped) {
                SimpleMessage message = new SimpleMessage();
                message.setBody("Task " + value++);
                queue.put(message);
                Thread.sleep(1000);  // Имитация работы
            }
            queue.put(new PoisonPillMessage());  // Отправка Poison Pill
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        isStopped = true;
    }
}
