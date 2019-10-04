package spring3;

import org.springframework.web.client.RestTemplate;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    final String entity = restTemplate
        .getForObject("http://www.google.com", String.class);

    System.out.println(entity.length());

    Util.checkSpan("java-spring-rest-template", 1);
  }
}
