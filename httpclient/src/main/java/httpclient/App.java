package httpclient;

import java.util.concurrent.TimeUnit;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class App {
  public static void main(String[] args) throws Exception {
    final CloseableHttpClient client = HttpClients.createDefault();
    client.execute(new HttpGet("http://www.google.com"));
    client.close();

    TimeUnit.SECONDS.sleep(30);
  }
}
