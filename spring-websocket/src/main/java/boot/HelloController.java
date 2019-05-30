package boot;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public String greeting(String message) {
    System.out.println("Greeting: " + message);
    return message.toUpperCase();
  }

}
