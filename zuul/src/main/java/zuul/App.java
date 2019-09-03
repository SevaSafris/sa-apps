package zuul;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import util.Util;

@EnableZuulProxy
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {

      RestTemplate restTemplate = new RestTemplate();
      final ResponseEntity<String> entity = restTemplate
          .getForEntity("http://localhost:8080", String.class);
      if (entity.getStatusCodeValue() != 200) {
        throw new RuntimeException("Zuul failed");
      }
      System.out.println(entity.getStatusCode());

      Util.checkSpan("zuul", 3); // it was 4 before

      System.exit(0);
    };
  }
}
