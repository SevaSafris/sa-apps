package tomcat;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class App {
  private static final Logger logger = Logger.getLogger(App.class.getName());
  private static final int serverPort = 9786;

  public static void main(final String[] args) throws Exception {
    final Tomcat tomcatServer = new Tomcat();
    tomcatServer.setPort(serverPort);

    final File baseDir = new File("tomcat");
    tomcatServer.setBaseDir(baseDir.getAbsolutePath());

    final File applicationDir = new File(new File(baseDir, "webapps"), "ROOT");
    applicationDir.mkdirs();

    final Context appContext = tomcatServer.addWebapp("", applicationDir.getAbsolutePath());

    // Following triggers creation of NoPluggabilityServletContext object during initialization
    ((StandardContext)appContext).addApplicationLifecycleListener(new ServletContextListener() {
      @Override
      public void contextInitialized(final ServletContextEvent e) {
      }

      @Override
      public void contextDestroyed(final ServletContextEvent e) {
      }
    });

    Tomcat.addServlet(appContext, "helloWorldServlet", new HttpServlet() {
      private static final long serialVersionUID = 6184640156851545023L;

      @Override
      public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
      }
    });
    appContext.addServletMappingDecoded("/hello", "helloWorldServlet");

    tomcatServer.start();
    logger.info("Tomcat server: http://" + tomcatServer.getHost().getName() + ":" + serverPort + "/");

    final Request request = new Request.Builder().url("http://localhost:" + serverPort + "/hello").build();
    final Response response = new OkHttpClient().newCall(request).execute();

    assertEquals(HttpServletResponse.SC_ACCEPTED, response.code());

    tomcatServer.stop();
  }
}