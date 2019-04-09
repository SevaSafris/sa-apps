package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class App {
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
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

    TimeUnit.SECONDS.sleep(30);

    channel.close();
    connection.close();
  }
}
