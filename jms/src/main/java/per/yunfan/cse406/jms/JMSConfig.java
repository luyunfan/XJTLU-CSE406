package per.yunfan.cse406.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Message queue config static class
 */
public final class JMSConfig {

    /**
     * static class can not create instance
     */
    private JMSConfig() {
    }

    /**
     * ActiveMQ connection factory singleton object
     */
    private static volatile ActiveMQConnectionFactory activeMQConnectionFactory = null;

    /**
     * @return get ActiveMQ connection factory object
     */
    public static ConnectionFactory getActiveMQConnectionFactory() {
        String username = "admin";
        String password = "admin";
        String brokerURL = "failover://tcp://localhost:61616";
        if (activeMQConnectionFactory == null) {
            synchronized (JMSConfig.class) {
                if (activeMQConnectionFactory == null) {
                    activeMQConnectionFactory = new ActiveMQConnectionFactory(
                            username, password, brokerURL
                    );
                    activeMQConnectionFactory.setTrustAllPackages(true);
                }
            }
        }
        return activeMQConnectionFactory;
    }
}
