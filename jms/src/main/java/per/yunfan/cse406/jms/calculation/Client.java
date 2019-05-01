package per.yunfan.cse406.jms.calculation;

import per.yunfan.cse406.jms.JMSConfig;

import javax.jms.*;

public class Client {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JMSConfig
                    .getActiveMQConnectionFactory()
                    .createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("CalculatorExpression");
            MessageProducer messageProducer = session.createProducer(destination);

            Expression<Integer> integerExpression = new Expression<>(100, 100, Operator.ADD);
            Expression<Double> doubleExpression = new Expression<>(10.5, 5.5, Operator.SUBTRACT);

            ObjectMessage integerMessage = session.createObjectMessage(integerExpression);
            ObjectMessage doubleMessage = session.createObjectMessage(doubleExpression);
            messageProducer.send(integerMessage);
            messageProducer.send(doubleMessage);
            System.out.println("Send expression successful: " + integerMessage.getObject());
            System.out.println("Send expression successful: " + doubleMessage.getObject());


            Destination destinationResult = session.createQueue("CalculatorResult");
            MessageConsumer resultConsumer = session.createConsumer(destinationResult);

            resultConsumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    TextMessage result = (TextMessage) message;
                    try {
                        System.out.println("Received result: " + result.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.err.println("Message is not text message!" + message);
                }
            });
            session.commit();
            System.out.println("Press enter to exit...");
            //noinspection ResultOfMethodCallIgnored
            System.in.read();

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
