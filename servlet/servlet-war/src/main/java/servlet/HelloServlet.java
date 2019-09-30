package servlet;

import io.opentracing.util.GlobalTracer;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter out = resp.getWriter();
    try {
      final Field field = GlobalTracer.get().getClass().getDeclaredField("tracer");
      field.setAccessible(true);
      final Object tracer = field.get(GlobalTracer.get());
      out.println("Tracer: " + tracer);

    } catch (Exception ignore) {
    }

    out.println("Active span: " + GlobalTracer.get().activeSpan());

    out.close();
  }
}
