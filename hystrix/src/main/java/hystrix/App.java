package hystrix;

import static java.util.concurrent.TimeUnit.SECONDS;

import feign.Feign;
import feign.Headers;
import feign.RequestLine;
import feign.Retryer;
import feign.Target;
import feign.hystrix.HystrixFeign;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    System.setProperty("hystrix.command.default.execution.timeout.enabled", "false");

    Feign feign = HystrixFeign.builder()
        .retryer(new Retryer.Default(100, SECONDS.toMillis(1), 2))
        .build();

    StringEntityRequest
        entity = feign.newInstance(
        new Target.HardCodedTarget<>(StringEntityRequest.class,
            "http://www.google.com"));
    final String res = entity.get();
    System.out.println(res == null);

    Util.checkSpan("feign", 1);
  }

  private interface StringEntityRequest {
    @RequestLine("GET")
    @Headers("Content-Type: application/json")
    String get();
  }
}
