package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {
  static final boolean IS_KAFKA_VERSION_SUPPORTED;

  static {
    boolean isSupported = false;
    try {
      Class.forName("org.apache.kafka.common.header.Headers");
      isSupported = true;
    } catch (ClassNotFoundException e) {
      System.out.println("Kafka prior version 1.0.0 is not supported");
    }
    IS_KAFKA_VERSION_SUPPORTED = isSupported;
  }


  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
