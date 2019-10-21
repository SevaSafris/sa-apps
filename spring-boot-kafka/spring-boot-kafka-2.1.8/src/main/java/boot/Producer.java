package boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
  private static final String TOPIC = "users";

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  void sendMessage(String message) {
    System.out.println(String.format("#### -> Producing message -> %s", message));
    this.kafkaTemplate.send(TOPIC, message);
  }
}
