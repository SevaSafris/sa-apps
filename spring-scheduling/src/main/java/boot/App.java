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
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Autowired
  private AsyncComponent asyncComponent;


  @Scheduled(fixedDelay = 30_000)
  public void scheduledTask() {
    final Span span = GlobalTracer.get().activeSpan();
    System.out.println("Active span: " + span);

    if (span == null) {
      System.err.println("No active span");
      System.exit(-1);
    }
  }


  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      final String str = asyncComponent.async().get(5, TimeUnit.SECONDS);
      System.out.println(str);

      TimeUnit.SECONDS.sleep(10);
      System.exit(0);
    };
  }

}
