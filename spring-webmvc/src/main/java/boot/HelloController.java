package boot;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    final Span span = GlobalTracer.get().activeSpan();
    System.out.println("Active span: " + span);
    return "Greetings from Spring Boot!";
  }

}
