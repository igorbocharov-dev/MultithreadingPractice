package Task6;

public class Consumer implements Runnable{

    private final MessageQueue queue;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Message message;
            while (true) {
                message = queue.take();
                if (message instanceof PoisonPillMessage) break;  // Остановка если Poison Pill
                processMessage(message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processMessage(Message message) {
        System.out.println("Processed: " + message.getBody());
    }
}
