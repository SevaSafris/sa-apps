package boot;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import util.Util;

@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Autowired
  private Sender sender;

  @Autowired
  private Receiver receiver;

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
      System.exit(0);

    };
  }
}
