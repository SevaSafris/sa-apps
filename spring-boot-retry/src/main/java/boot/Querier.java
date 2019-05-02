package boot;

import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class Querier {

  @Autowired
  private HttpClient httpClient;

  @Retryable(interceptor = "retryInterceptor")
  public void get() throws IOException {
    System.out.println("Execute");
    httpClient.execute(new HttpGet("localhost:9999"));
  }

}
