package Task6;

public interface Message {
    void addHeader(String key, String value);
    String getHeader(String key);
    String getBody();
    void setBody(String body);
}
