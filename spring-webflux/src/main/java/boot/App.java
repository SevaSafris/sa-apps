package boot;

import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;
import io.opentracing.util.GlobalTracerTestUtil;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class App {
  private static final MockTracer tracer = new MockTracer();

  public static void main(String[] args) {
    GlobalTracerTestUtil.setGlobalTracerUnconditionally(tracer);

    SpringApplication.run(App.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner() {
    return args -> {

      final WebClient client = WebClient.builder().baseUrl("http://localhost:8080")
          .build();

      final ClientResponse response = client.get().exchange().block();

      System.out.println(response.bodyToMono(String.class).block());

      System.out.println("Spans: " + tracer.finishedSpans());

      //TimeUnit.SECONDS.sleep(30);
      System.exit(0);

    };
  }

}
