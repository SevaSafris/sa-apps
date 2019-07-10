package boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import util.Util;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {
      final WebClient client = WebClient.builder().baseUrl("http://localhost:8080")
          .build();

      final ClientResponse response = client.get().exchange().block();

      System.out.println(response.bodyToMono(String.class).block());

      Util.checkSpan("java-spring-webclient", 1);
      System.exit(0);
    };
  }

}
