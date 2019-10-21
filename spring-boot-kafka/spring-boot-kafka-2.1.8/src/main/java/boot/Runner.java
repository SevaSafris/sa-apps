package boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
  private final Producer producer;

  @Autowired
  public Runner(Producer producer) {
    this.producer = producer;
  }


  @Override
  public void run(String... args) throws InterruptedException {
    producer.sendMessage("Message ");
  }
}
