package boot;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @Autowired
  private Querier querier;

  @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
  public String test() {
    try {
      querier.get();
    } catch (IOException e) {
      return "exception";
    }

    return "success";
  }

}
