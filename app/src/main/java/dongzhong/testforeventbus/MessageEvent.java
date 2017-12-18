package dongzhong.testforeventbus;

/**
 * Created by dongzhong on 2017/12/18.
 */

public class MessageEvent {
    private String eventType;

    public MessageEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
