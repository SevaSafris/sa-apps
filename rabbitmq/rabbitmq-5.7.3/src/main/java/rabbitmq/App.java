package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import util.Util;

public class App {
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) throws Exception {
    final EmbeddedAMQPBroker broker = new EmbeddedAMQPBroker();

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUsername("guest");
    factory.setPassword("guest");
    factory.setHost("localhost");
    factory.setPort(broker.getBrokerPort());
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Hello World!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    System.out.println(" [x] Sent '" + message + "'");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String messageReceived = new String(delivery.getBody(), StandardCharsets.UTF_8);
      System.out.println(" [x] Received '" + messageReceived + "'");
    };
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
    });

    TimeUnit.SECONDS.sleep(10);

    channel.close();
    connection.close();
    broker.shutdown();

    Util.checkSpan("java-rabbitmq", 2);
  }
}
