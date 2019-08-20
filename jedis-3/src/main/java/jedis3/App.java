package jedis3;

import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final RedisServer server = new RedisServer();
    server.start();

    Jedis jedis = new Jedis();
    jedis.set("key", "value");
    System.out.println(jedis.get("key"));

    server.stop();

    Util.checkSpan("java-redis", 2);
    System.exit(0);
  }
}
