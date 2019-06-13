package redisson;

import java.util.concurrent.TimeUnit;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class App {
  public static void main(String[] args) throws InterruptedException {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");

    RedissonClient redissonClient = null;
    try {
      redissonClient = Redisson.create(config);
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(-1);
    }

    RMap<String, String> map = redissonClient.getMap("map");

    map.put("key", "value");
    System.out.println("value=" + map.get("key"));

    redissonClient.shutdown();

    TimeUnit.SECONDS.sleep(30);
  }
}
