package per.yunfan.cse406.jms.logger;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 一个代表事件的Java Bean
 */
public class Event implements Serializable {

    /**
     * 出现事件的客户端ID
     */
    private final String clientId;

    /**
     * 出现事件的时间
     */
    private final Date date;

    /**
     * 事件消息
     */
    private final String message;

    public Event(String clientId, Date date, String message) {
        this.clientId = clientId;
        this.date = date;
        this.message = message;
    }

    public String getClientId() {
        return clientId;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "clientId='" + clientId + '\'' +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(clientId, event.clientId) &&
                Objects.equals(date, event.date) &&
                Objects.equals(message, event.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, date, message);
    }
}
