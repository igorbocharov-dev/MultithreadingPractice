package Task6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleMessageQueue implements MessageQueue {

    private BlockingQueue<Message> queue;

    public SimpleMessageQueue(int capacity){
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public void put(Message message) throws InterruptedException {
        queue.put(message);
    }

    @Override
    public Message take() throws InterruptedException {
        return queue.take();
    }
}
