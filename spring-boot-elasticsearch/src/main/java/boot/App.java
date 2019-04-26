package boot;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {

  @Bean
  public RestClient client() {
    return RestClient.builder(
        new HttpHost("localhost", 9200, "http"))
        .build();
  }


  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
