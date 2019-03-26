package okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class App {
  public static void main(String[] args) throws Exception {
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
        .url("https://www.google.com")
        .build();

    Response response = client.newCall(request).execute();

    System.out.println(response.code());

    Thread.sleep(30_000);
  }
}
