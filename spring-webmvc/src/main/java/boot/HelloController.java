package boot;

import io.opentracing.util.GlobalTracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("Missing active span");
      System.exit(-1);
    }
    return "Greetings from Spring Boot!";
  }

}
