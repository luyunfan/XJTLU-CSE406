package per.yunfan.cse406.jms.logger;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;

/**
 * 记录日志的工具类
 */
public final class Logger {

    /**
     * 工具类不能实例化
     */
    private Logger() {
    }

    /**
     * 像消息队列记录事件
     *
     * @param message 事件对象
     */
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
