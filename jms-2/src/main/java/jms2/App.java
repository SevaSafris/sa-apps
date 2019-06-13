package jms2;


import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;

public class App {
  public static void main(String[] args) throws Exception {

    final ActiveMQJMSConnectionFactory connectionFactory =
        new ActiveMQJMSConnectionFactory("tcp://localhost:61616");
    connectionFactory.setUser("artemis");
    connectionFactory.setPassword("simetraehcapa");

    Connection connection = connectionFactory.createConnection();
    connection.start();
    JMSContext context = connectionFactory.createContext();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    System.out.println("Session: " + session);

    final Destination destination = session.createQueue("TEST.FOO2");

    final JMSProducer producer = context.createProducer();
    System.out.println("PRODUCER: " + producer);

    final MessageConsumer consumer = session.createConsumer(destination);
    System.out.println("CONSUMER: " + consumer);

    final TextMessage message = session.createTextMessage("Hello world");

    producer.send(destination, message);

    final TextMessage received = (TextMessage) consumer.receive(5000);
    System.out.println("RECEIVED: " + received.getText());

    context.close();
    session.close();
    connection.close();

    TimeUnit.SECONDS.sleep(10);
  }

}