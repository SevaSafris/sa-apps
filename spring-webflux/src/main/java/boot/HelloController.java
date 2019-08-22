package boot;

import io.opentracing.util.GlobalTracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

  @RequestMapping("/")
  public Mono<String> index() {
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("ERROR: no active span");
      System.exit(-1);
    }
    System.out.println("Active span: " + GlobalTracer.get().activeSpan());
    return Mono.just("Greetings from Spring Boot!");
  }

}
