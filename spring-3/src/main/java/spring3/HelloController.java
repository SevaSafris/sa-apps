package spring3;

import io.opentracing.util.GlobalTracer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String printWelcome(ModelMap model) {
    System.out.println("Active Span: " + GlobalTracer.get().activeSpan());
    return "hello";
  }

}
