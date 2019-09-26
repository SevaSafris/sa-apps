package spring4;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    Server server = startServer();

    RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<String> responseEntity = restTemplate
        .getForEntity("http://localhost:8080", String.class);

    System.out.println(responseEntity.getStatusCode());

    final AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
    final ResponseEntity<String> asyncEntity = asyncRestTemplate
        .getForEntity("http://localhost:8080", String.class)
        .get(10, TimeUnit.SECONDS);

    System.out.println(asyncEntity.getStatusCode());

    server.stop();

    Util.checkSpan("java-web-servlet", 2);
  }

  private static Server startServer() {
    Server server = new Server(8080);

    final WebAppContext ctx = new WebAppContext();
    ctx.setServer(server);
    ctx.setContextPath("/");
    try {
      if (new File(".").getCanonicalFile().getName().equals("malafeev-sa-apps")) {
        ctx.setWar("/projects/malafeev-sa-apps/spring-webmvc-4.3.24/src/main/webapp");
      } else {
        ctx.setWar("src/main/webapp");
      }
      System.out.println(new File(".").getAbsolutePath());

//      ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
//          ".*/[^/]*jstl.*\\.jar$");
//
//      org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList
//          .setServerDefault(server);
//      classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
//          "org.eclipse.jetty.plus.webapp.EnvConfiguration",
//          "org.eclipse.jetty.plus.webapp.PlusConfiguration");
//      classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
//          "org.eclipse.jetty.annotations.AnnotationConfiguration");

      server.setHandler(ctx);
      server.start();
      System.out.println("Started");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return server;
  }
}
