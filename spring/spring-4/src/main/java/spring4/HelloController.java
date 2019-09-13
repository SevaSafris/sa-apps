package spring4;

import io.opentracing.util.GlobalTracer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody
  public String printWelcome(ModelMap model) {
    System.out.println("Active Span: " + GlobalTracer.get().activeSpan());
    if (GlobalTracer.get().activeSpan() == null) {
      System.err.println("ERROR: no active span");
      System.exit(-1);
    }
    return "hello";
  }

}
