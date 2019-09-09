package httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final HttpClient client = HttpClientBuilder.create().build();
    final HttpResponse response = client.execute(new HttpGet("http://www.google.com"));
    System.out.println(response.getStatusLine().getReasonPhrase());

    Util.checkSpan("java-httpclient", 1);
  }
}
