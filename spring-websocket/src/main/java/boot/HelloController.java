package boot;

import io.opentracing.util.GlobalTracer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public String greeting(String message) {
    System.out.println("Greeting: " + message);
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("ERROR: no active span");
      System.exit(-1);
    }
    System.out.println("Active span: " + GlobalTracer.get().activeSpan());
    return message.toUpperCase();
  }

}
