package boot;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class App implements CommandLineRunner {
  @Autowired
  private StringRedisTemplate template;

  @Override
  public void run(String... args) throws InterruptedException {
    ValueOperations<String, String> ops = this.template.opsForValue();
    ops.set("key", "value");
    System.out.println("value=" + ops.get("key"));
    TimeUnit.SECONDS.sleep(30);
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args).close();
  }
}
