package jedis3;

import redis.clients.jedis.Jedis;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    Jedis jedis = new Jedis();
    jedis.set("key", "value");
    System.out.println(jedis.get("key"));

    Util.checkSpan("java-redis", 2);
  }
}
