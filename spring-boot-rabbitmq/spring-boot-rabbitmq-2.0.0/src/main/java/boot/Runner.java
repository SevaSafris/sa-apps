package boot;

import java.util.concurrent.TimeUnit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Sending message to Queue 1...");
    rabbitTemplate.convertAndSend(App.topicExchangeName, "foo.bar.baz", "Message for Queue 1");

    System.out.println("Sending message to Queue 2...");
    rabbitTemplate.convertAndSend(App.queueName2, "Message for Queue 2");

    TimeUnit.SECONDS.sleep(10);
  }

}