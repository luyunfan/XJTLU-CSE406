package per.yunfan.cse406.jms.p2p;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;
import java.util.Date;

/**
 * 点对点模型生产者服务器主类
 */
public class Producer {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("Queue Test");
            MessageProducer messageProducer = session.createProducer(destination);

            for (int i = 0; i < 10; i++) {
                TextMessage message = session.createTextMessage("Queue Test Message " + new Date());
                messageProducer.send(message);
                System.out.println("Send successful: " + message.getText());
            }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
