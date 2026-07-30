package Task6;

public interface MqPublishPoint {
    void put(Message message) throws InterruptedException;
}
