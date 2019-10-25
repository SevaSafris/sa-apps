package grizzlyhttpclient;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.ning.http.client.SimpleAsyncHttpClient;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final AsyncHttpClient client = new AsyncHttpClient();
    final Response response = client.prepareGet("http://www.google.com").execute().get();
    System.out.println(response.getStatusText());

    SimpleAsyncHttpClient simpleAsyncHttpClient = new SimpleAsyncHttpClient.Builder()
        .setUrl("http://www.google.com")
        .build();

    final Response response2 = simpleAsyncHttpClient.get().get();
    System.out.println(response2.getStatusText());

    Util.checkSpan("java-grizzly-ahc", 2);
  }
}
