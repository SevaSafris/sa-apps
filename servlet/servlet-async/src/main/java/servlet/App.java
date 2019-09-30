package servlet;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import util.Util;

public class App {

  public static void main(String[] args) throws Exception {
    Server server = startServer();

    async();

    server.stop();

    Util.checkSpan("java-web-servlet", 1);
  }

  private static void async() throws Exception {
    URL obj = new URL("http://localhost:8080/async");
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    int responseCode = con.getResponseCode();
    System.out.println("Response Code : " + responseCode);
    try {
      final String output = IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
      System.out.println("Output: " + output);
    } catch (Throwable ignore) {

    }

    try {
      final String output = IOUtils.toString(con.getErrorStream(), StandardCharsets.UTF_8);
      System.out.println("Output: " + output);
    } catch (Throwable ignore) {

    }
  }

  private static Server startServer() {
    Server server = new Server(8080);

    final WebAppContext ctx = new WebAppContext();
    ctx.setServer(server);
    ctx.setContextPath("/");
    try {
      if (!new File(".").getCanonicalFile().getName().equals("servlet-async")) {
        ctx.setWar("servlet/servlet-async/src/main/webapp");
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
