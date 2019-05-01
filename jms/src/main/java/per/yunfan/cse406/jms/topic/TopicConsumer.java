package per.yunfan.cse406.jms.topic;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;
import java.io.IOException;

/**
 * Subscriber client
 */
public class TopicConsumer {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();

            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Topic Test");
            MessageConsumer messageConsumer = session.createConsumer(topic);

            messageConsumer.setMessageListener(message -> {
                try {
                    System.out.println("Received Topic message: " + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Press enter to exit...");
            //noinspection ResultOfMethodCallIgnored
            System.in.read();  //wait and avoid exit
        } catch (JMSException | IOException e) {
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