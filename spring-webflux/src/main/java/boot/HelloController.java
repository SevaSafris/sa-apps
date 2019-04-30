package boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

  @RequestMapping("/")
  public Mono<String> index() {
    return Mono.just("Greetings from Spring Boot!");
  }

}
