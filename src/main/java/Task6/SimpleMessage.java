package Task6;

import java.util.HashMap;
import java.util.Map;

public class SimpleMessage implements Message{

    private Map<String, String> headers = new HashMap<>();
    private String body;

    @Override
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public String getHeader(String key) {
        return headers.get(key);
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getBody() {
        return body;
    }
}
