package boot;

import java.util.concurrent.TimeUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class App {

  public static void main(String[] args) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<String> entity = restTemplate
        .getForEntity("http://www.google.com", String.class);

    System.out.println(entity.getStatusCode());

    TimeUnit.SECONDS.sleep(30);
  }

}

