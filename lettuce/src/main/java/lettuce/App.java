package lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import java.util.concurrent.TimeUnit;

public class App {
  public static void main(String[] args) throws Exception {
    RedisClient client = RedisClient.create("redis://localhost");
    StatefulRedisConnection<String, String> connection = client.connect();
    RedisCommands<String, String> commands = connection.sync();
    System.out.println(commands.set("key", "value"));
    System.out.println(commands.get("key"));

    connection.close();

    client.shutdown();
    TimeUnit.SECONDS.sleep(30);
  }
}
