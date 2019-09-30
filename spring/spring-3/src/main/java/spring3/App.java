package spring3;

import java.io.File;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    Server server = startServer();

    RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<String> responseEntity = restTemplate
        .getForEntity("http://localhost:8080", String.class);

    System.out.println(responseEntity.getStatusCode());

    server.stop();

    Util.checkSpan("java-web-servlet", 2);
  }

  private static Server startServer() {
    Server server = new Server(8080);

    final WebAppContext ctx = new WebAppContext();
    ctx.setServer(server);
    ctx.setContextPath("/");
    try {
      if (!new File(".").getCanonicalFile().getName().equals("spring-3")) {
        ctx.setWar("spring/spring-3/src/main/webapp");
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
