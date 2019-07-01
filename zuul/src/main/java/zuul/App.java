package zuul;

import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockTracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

      TimeUnit.SECONDS.sleep(10);
      checkZuulSpan();

      System.exit(0);
    };
  }

  private static void checkZuulSpan() throws Exception {
    final Field field = GlobalTracer.get().getClass().getDeclaredField("tracer");
    field.setAccessible(true);
    final Object ob = field.get(GlobalTracer.get());
    MockTracer tracer;
    if (ob instanceof MockTracer) {
      tracer = (MockTracer) ob;
    } else {
      return;
    }
    boolean found = false;
    for (MockSpan span : tracer.finishedSpans()) {
      if (span.tags().get(Tags.COMPONENT.getKey()).equals("zuul")) {
        found = true;
        System.out.println("Found zuul span");
        break;
      }
    }
    if (!found) {
      throw new RuntimeException("Zuul span not found");
    }

    if (tracer.finishedSpans().size() != 4) {
      throw new RuntimeException(tracer.finishedSpans().size() +
          " spans instead of 4");
    }
  }

}
