package boot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import util.Util;

public class App {

  public static void main(String[] args) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<String> entity = restTemplate
        .getForEntity("http://www.google.com", String.class);

    System.out.println(entity.getStatusCode());

    Util.checkSpan("java-spring-rest-template", 1);
  }


}

