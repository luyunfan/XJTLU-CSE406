package per.yunfan.cse406.jms.logger;

import java.util.Date;

/**
 * 产生事件的第二个生产者客户端
 */
public class Client002 {
    public static void main(String[] args) {
        Event event = new Event("002", new Date(), "message from client-002");
        Logger.info(event);
    }
}
