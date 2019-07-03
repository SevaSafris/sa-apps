package boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
  private final Producer producer;
  private int counter;

  @Autowired
  public ScheduledTask(Producer producer) {
    this.producer = producer;
  }

  @Scheduled(fixedDelay = 5000)
  public void task() {
    producer.sendMessage("Message " + counter);
    counter++;
    if (counter == 3) {
      System.exit(0);
    }
  }
}
