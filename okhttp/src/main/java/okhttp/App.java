package okhttp;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class App {
  public static void main(String[] args) throws Exception {
    test(new OkHttpClient.Builder().build());
    test(new OkHttpClient());

    Thread.sleep(30_000);
  }

  private static void test(OkHttpClient client) throws IOException {
    Request request = new Request.Builder()
        .url("https://www.google.com")
        .build();

    Response response = client.newCall(request).execute();
    System.out.println(response.code());
  }
}
