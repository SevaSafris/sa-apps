package servlet;

import io.opentracing.util.GlobalTracer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Util;

public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Util.checkActiveSpan();

    PrintWriter out = resp.getWriter();
    out.println("Sync Servlet active span: " + GlobalTracer.get().activeSpan());

    out.close();
  }
}
