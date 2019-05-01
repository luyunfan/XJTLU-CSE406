package per.yunfan.cse406.jms.topic;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;
import java.util.Date;

/**
 * Publisher server
 */
public class TopicProducer {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Topic Test");
            MessageProducer messageProducer = session.createProducer(topic);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message;
            for (int i = 0; i < 10; i++) {
                message = session.createTextMessage("Test topic message " + new Date());
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