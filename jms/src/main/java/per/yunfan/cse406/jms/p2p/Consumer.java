package per.yunfan.cse406.jms.p2p;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;

/**
 * 点对点模型消费者客户端主类
 */
public class Consumer {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();

            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("Queue Test");
            MessageConsumer messageConsumer = session.createConsumer(destination);

            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage != null) {
                    System.out.println("Received message: " + textMessage.getText());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
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
