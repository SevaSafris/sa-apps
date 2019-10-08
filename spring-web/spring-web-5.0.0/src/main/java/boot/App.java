package boot;

import java.util.concurrent.TimeUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import util.Util;

public class App {

  public static void main(String[] args) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<String> entity = restTemplate
        .getForEntity("http://www.google.com", String.class);

    System.out.println(entity.getStatusCode());

    final AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
    final ResponseEntity<String> asyncEntity = asyncRestTemplate
        .getForEntity("http://www.google.com", String.class)
        .get(10, TimeUnit.SECONDS);

    System.out.println(asyncEntity.getStatusCode());

    asyncRestTemplate.getForEntity("http://www.google.com", String.class)
        .addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
          @Override
          public void onSuccess(ResponseEntity<String> result) {
            Util.checkActiveSpan();
          }

          @Override
          public void onFailure(Throwable t) {
            Util.checkActiveSpan();
          }
        });

    Util.checkSpan("java-spring-rest-template", 3);
  }


}

