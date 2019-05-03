package per.yunfan.cse406.jms.object;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;
import java.io.IOException;

/**
 * 使用JSM发送对象的消费者客户端主类
 */
public class ObjectConsumer {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();

            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TransQueue");
            MessageConsumer messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(message -> {
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        PaymentEvent paymentEvent = (PaymentEvent) objectMessage.getObject();
                        System.out.println("Received payment: " + paymentEvent);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("Message is not object message!" + message);
                }
            });

            System.out.println("Press enter to exit...");
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
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
