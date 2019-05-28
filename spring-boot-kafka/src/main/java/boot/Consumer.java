package boot;

import java.io.IOException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

  @KafkaListener(topics = "users", groupId = "group_id")
  @SendTo("reply")
  public String consume(String message) throws IOException {
    System.out.println(String.format("1. #### -> Consumed message -> %s", message));
    return message.toUpperCase();
  }

  @KafkaListener(topics = "reply", groupId = "group_id")
  public void consume2(String message) throws IOException {
    System.out.println(String.format("2. #### -> Consumed message -> %s", message));
  }
}
