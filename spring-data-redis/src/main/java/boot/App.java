package boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.embedded.RedisServer;
import util.Util;

@SpringBootApplication
public class App implements CommandLineRunner {
  @Autowired
  private StringRedisTemplate template;

  @Override
  public void run(String... args) throws Exception {
    final RedisServer server = new RedisServer();
    server.start();

    ValueOperations<String, String> ops = this.template.opsForValue();
    ops.set("key", "value");
    System.out.println("value=" + ops.get("key"));

    server.stop();
    Util.checkSpan("java-redis", 2);
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args).close();
    System.exit(0);
  }
}
