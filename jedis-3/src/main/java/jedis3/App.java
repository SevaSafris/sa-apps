package jedis3;

import java.util.concurrent.TimeUnit;
import redis.clients.jedis.Jedis;

public class App {
  public static void main(String[] args) throws Exception {
    Jedis jedis = new Jedis();
    jedis.set("key", "value");
    System.out.println(jedis.get("key"));

    TimeUnit.SECONDS.sleep(10L);
  }
}
