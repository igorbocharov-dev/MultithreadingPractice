package Task6;

public interface MqSubscribePoint {
    Message take() throws InterruptedException;
}
