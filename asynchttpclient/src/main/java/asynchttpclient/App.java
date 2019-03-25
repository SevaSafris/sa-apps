package asynchttpclient;

import java.util.concurrent.TimeUnit;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.asynchttpclient.util.HttpConstants;

public class App {
  public static void main(String[] args) throws Exception {
    AsyncHttpClient client = new DefaultAsyncHttpClient();

    Request request = new RequestBuilder(HttpConstants.Methods.GET)
        .setUrl("http://www.google.com")
        .build();

    final Response response = client.executeRequest(request).get(10, TimeUnit.SECONDS);
    System.out.println(response.getStatusText());

    Thread.sleep(30_000);

    System.exit(0);
  }
}
