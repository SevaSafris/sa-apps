package jms;


import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
        "vm://localhost?broker.persistent=false");
    final Connection connection = connectionFactory.createConnection();
    connection.start();

    jms(connection);

    TimeUnit.SECONDS.sleep(10);
    connection.close();
    Util.checkSpan("java-jms", 4);
  }

  private static void jms(Connection connection) {
    thread(new HelloWorldProducer(connection), false);
    thread(new HelloWorldProducer(connection), false);
    thread(new HelloWorldConsumer(connection), false);
    thread(new HelloWorldConsumer(connection), false);
  }

  public static class HelloWorldProducer implements Runnable {
    private final Connection connection;

    public HelloWorldProducer(Connection connection) {
      this.connection = connection;
    }

    public void run() {
      try {
        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("Session: " + session);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("TEST.FOO");

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        System.out.println("PRODUCER: " + producer);

        // Create a messages
        String text =
            "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
        TextMessage message = session.createTextMessage(text);

        // Tell the producer to send the message
        System.out.println(
            "Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
        producer.send(message);

        // Clean up
        session.close();
      } catch (Exception e) {
        System.out.println("Caught: " + e);
        e.printStackTrace();
      }
    }
  }

  public static class HelloWorldConsumer implements Runnable, ExceptionListener {
    private final Connection connection;

    public HelloWorldConsumer(Connection connection) {
      this.connection = connection;
    }

    public void run() {
      try {

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("TEST.FOO");

        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(destination);
        System.out.println("CONSUMER: " + consumer);

        // Wait for a message
        Message message = consumer.receive(1000);

        if (message instanceof TextMessage) {
          TextMessage textMessage = (TextMessage) message;
          String text = textMessage.getText();
          System.out.println("Received: " + text);
        } else {
          System.out.println("Received: " + message);
        }

        consumer.close();
        session.close();
      } catch (Exception e) {
        System.out.println("Caught: " + e);
        e.printStackTrace();
      }
    }

    public synchronized void onException(JMSException ex) {
      System.out.println("JMS Exception occured.  Shutting down client.");
    }
  }

  private static void thread(Runnable runnable, boolean daemon) {
    Thread brokerThread = new Thread(runnable);
    brokerThread.setDaemon(daemon);
    brokerThread.start();
  }
}
