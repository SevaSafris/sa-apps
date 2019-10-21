package boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import util.Util;

@Component
public class Runner implements CommandLineRunner {
  private final Producer producer;

  @Autowired
  public Runner(Producer producer) {
    this.producer = producer;
  }

  @Override
  public void run(String... args) throws Exception {
    producer.sendMessage("Message ");

    Util.checkSpan("java-kafka", 6);
    System.exit(0);

  }
}
