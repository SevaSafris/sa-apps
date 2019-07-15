package boot;

import io.opentracing.util.GlobalTracer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

  @KafkaListener(topics = "users")
  @SendTo("reply")
  public String consume(String message) {
    System.out.println(String.format("1. #### -> Consumed message -> %s", message));
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("No active span");
      if(App.IS_KAFKA_VERSION_SUPPORTED) {
        System.exit(-1);
      }
    }
    System.out.println("Active span: " + GlobalTracer.get().activeSpan());
    return message.toUpperCase();
  }

  @KafkaListener(topics = "reply")
  public void consume2(String message) {
    System.out.println(String.format("2. #### -> Consumed message -> %s", message));
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("No active span");
      if(App.IS_KAFKA_VERSION_SUPPORTED) {
        System.exit(-1);
      }
    }
    System.out.println("Active span: " + GlobalTracer.get().activeSpan());
  }
}
