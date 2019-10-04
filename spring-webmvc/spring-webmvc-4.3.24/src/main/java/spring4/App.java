package spring4;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    Server server = startServer();

    URL obj = new URL("http://localhost:8080");
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    System.out.println("Response Code : " + responseCode);

    server.stop();

    Util.checkSpan("java-web-servlet", 1);
  }

  private static Server startServer() {
    Server server = new Server(8080);

    final WebAppContext ctx = new WebAppContext();
    ctx.setServer(server);
    ctx.setContextPath("/");
    try {
      if (!new File(".").getCanonicalFile().getName().equals("spring-webmvc-4.3.24")) {
        ctx.setWar("spring/spring-webmvc-4.3.24/src/main/webapp");
      } else {
        ctx.setWar("src/main/webapp");
      }
      System.out.println(new File(".").getAbsolutePath());

      server.setHandler(ctx);
      server.start();
      System.out.println("Started");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return server;
  }
}
