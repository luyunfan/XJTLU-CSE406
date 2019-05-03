package per.yunfan.cse406.jms.object;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;
import java.math.BigDecimal;

/**
 * 使用JSM发送对象的生产者服务器主类
 */
public class ObjectProducer {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TransQueue");
            MessageProducer messageProducer = session.createProducer(destination);

            PaymentEvent paymentEvent = new PaymentEvent("0", "payer", new BigDecimal("3.0"));

            ObjectMessage message = session.createObjectMessage(paymentEvent);
            messageProducer.send(message);
            System.out.println("Send successful: " + message.getObject());

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
