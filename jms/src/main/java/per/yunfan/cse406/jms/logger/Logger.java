package per.yunfan.cse406.jms.logger;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;

public final class Logger {

    private Logger() {
    }

    public static void info(Event message) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("Logger");
            MessageProducer messageProducer = session.createProducer(topic);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            ObjectMessage objectMessage = session.createObjectMessage(message);
            messageProducer.send(objectMessage);
            System.out.println("Send successful: " + objectMessage.getObject());
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
