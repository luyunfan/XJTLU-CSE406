package per.yunfan.cse406.jms.calculation;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 使用JMS实现RPC调用的服务端主类
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
            Destination destination = session.createQueue("CalculatorExpression");
            MessageConsumer messageConsumer = session.createConsumer(destination);

            final Connection finalConnection = connection;

            messageConsumer.setMessageListener(message -> {
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        Expression<?> expression = (Expression<?>) objectMessage.getObject();
                        System.out.println("Server received expression: " + expression);
                        BigDecimal first = new BigDecimal(expression.getFirst().doubleValue());
                        BigDecimal second = new BigDecimal(expression.getSecond().doubleValue());
                        BigDecimal result = new BigDecimal(0);
                        switch (expression.getOperator()) {
                            case ADD:
                                result = first.add(second);
                                break;
                            case SUBTRACT:
                                result = first.subtract(second);
                                break;
                            case MULTIPLY:
                                result = first.multiply(second);
                                break;
                            case DIVIDE:
                                result = first.divide(second, RoundingMode.FLOOR);
                                break;
                        }

                        Session sessionResult = finalConnection.createSession(true, Session.AUTO_ACKNOWLEDGE);
                        Destination destinationResult = sessionResult.createQueue("CalculatorResult");
                        MessageProducer messageProducer = sessionResult.createProducer(destinationResult);
                        TextMessage resultMessage = session.createTextMessage(String.valueOf(result));
                        messageProducer.send(resultMessage);
                        System.out.println("Send result successful: " + result);
                        sessionResult.commit();
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
