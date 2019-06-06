package boot;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Autowired
  private RestTemplate restTemplate;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }


  @Scheduled(fixedDelay = 1000)
  public void scheduledTask() {
    final Span span = GlobalTracer.get().activeSpan();
    System.out.println("Active span: " + span);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      final ResponseEntity<String> entity = restTemplate
          .getForEntity("http://localhost:8080", String.class);

      System.out.println(entity);

      TimeUnit.SECONDS.sleep(30);
      System.exit(0);

    };
  }

}
