package httpclient;

import java.util.concurrent.TimeUnit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class App {
  public static void main(String[] args) throws Exception {
    final CloseableHttpClient client = HttpClients.createDefault();
    final CloseableHttpResponse response = client.execute(new HttpGet("http://www.google.com"));
    System.out.println(response.getStatusLine().getReasonPhrase());
    client.close();

    TimeUnit.SECONDS.sleep(10);
  }
}
