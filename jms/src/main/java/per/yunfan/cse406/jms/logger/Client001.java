package per.yunfan.cse406.jms.logger;

import java.util.Date;

public class Client001 {
    public static void main(String[] args) {
        Event event = new Event("001", new Date(), "message from client-001");
        Logger.info(event);
    }
}
