package servlet;

import io.opentracing.util.GlobalTracer;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAsyncServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    final AsyncContext asyncContext = req.startAsync(req, resp);

    new Thread() {

      @Override
      public void run() {
        try {

          ServletResponse response = asyncContext.getResponse();
          response.setContentType("text/plain");
          PrintWriter out = response.getWriter();
          out.println("Async Servlet active span: " + GlobalTracer.get().activeSpan());
          out.flush();
          asyncContext.complete();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }.start();
  }
}
