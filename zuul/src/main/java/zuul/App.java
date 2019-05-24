package zuul;

import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.ZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class App {
  public static void main(String[] args) {

//    for (ZuulFilter filter : FilterLoader.getInstance().getFiltersByType("pre")) {
//      System.out.println(filter);
//    }

    SpringApplication.run(App.class, args);
  }
}
