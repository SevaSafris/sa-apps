package boot;

import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import util.Util;

@SpringBootApplication
public class App {
  private static EmbeddedAMQPBroker broker;

  public static void main(String[] args) throws Exception {
    broker = new EmbeddedAMQPBroker();

    SpringApplication.run(App.class, args).close();

    broker.shutdown();
    System.exit(0);
  }

  @Autowired
  private Sender sender;

  @Autowired
  private Receiver receiver;

  @Bean
  public ConnectionFactory connectionFactory() {
    final CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
    factory.setUsername("guest");
    factory.setPassword("guest");
    factory.setHost("localhost");
    factory.setPort(broker.getBrokerPort());
    return factory;
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      sender.send("Ping");
      int counter = 0;
      while (receiver.getReceivedMessages().size() == 0 && counter < 10) {
        TimeUnit.SECONDS.sleep(1);
        counter++;
      }

      System.out.println("Received: " + receiver.getReceivedMessages());
      Util.checkSpan("spring-messaging", 5);
    };
  }
}
