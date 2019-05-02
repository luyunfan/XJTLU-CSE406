package per.yunfan.cse406.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.ConnectionFactory;

/**
 * 消息队列配置信息工具类
 */
public final class JMSConfig {

    /**
     * 工具类不产生实例
     */
    private JMSConfig() {
    }

    /**
     * ActiveMQ connection factory 单例对象
     */
    private static volatile ActiveMQConnectionFactory activeMQConnectionFactory = null;

    /**
     * @return 获取 ActiveMQ connection factory 单例对象
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
