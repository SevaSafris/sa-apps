package feign;


import feign.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {

    Feign feign = getClient();

    StringEntityRequest
        entity = feign.newInstance(new Target.HardCodedTarget<>(StringEntityRequest.class,
        "http://www.google.com"));
    final String res = entity.get();
    System.out.println(res == null);

    Util.checkSpan("feign", 2);
  }

  private static Feign getClient() {
    return Feign.builder()
        .client((new OkHttpClient()))
        .retryer(new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 2))
        .build();


  }

  private interface StringEntityRequest {
    @RequestLine("GET")
    @Headers("Content-Type: application/json")
    String get();
  }
}
