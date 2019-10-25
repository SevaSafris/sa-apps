package grizzlyhttpserver;

import static org.glassfish.grizzly.http.server.NetworkListener.DEFAULT_NETWORK_HOST;

import java.net.HttpURLConnection;
import java.net.URL;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final HttpServer server = new HttpServer();
    NetworkListener listener = new NetworkListener("grizzly", DEFAULT_NETWORK_HOST, 18906);
    server.addListener(listener);
    server.start();

    server.getServerConfiguration().addHttpHandler(new HttpHandler() {
      @Override
      public void service(final Request request, final Response response) {
        Util.checkActiveSpan();
        response.setStatus(201);
      }
    });

    URL obj = new URL("http://localhost:18906/");
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    System.out.println("Response Code : " + responseCode);

    server.shutdownNow();
    System.out.println();
    Util.checkSpan("java-grizzly-http-server", 1);
  }
}
