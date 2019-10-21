package boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final JmsTemplate jmsTemplate;

  public Runner(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    jmsTemplate.convertAndSend("mailbox", "hello");
  }

}