package per.yunfan.cse406.jms.logger;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;
import java.io.IOException;

/**
 * 记录日志的消费者主类
 */
public class Server {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();

            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Logger");
            MessageConsumer messageConsumer = session.createConsumer(topic);

            messageConsumer.setMessageListener(message -> {
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        System.out.println("Received log event message: " + objectMessage.getObject());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
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
