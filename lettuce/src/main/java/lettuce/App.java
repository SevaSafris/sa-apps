package lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import java.util.concurrent.TimeUnit;

public class App {
  public static void main(String[] args) throws Exception {
    RedisClient client = RedisClient.create("redis://localhost");
    StatefulRedisConnection<String, String> connection = client.connect();
    RedisCommands<String, String> commands = connection.sync();
    System.out.println(commands.set("key", "value"));
    System.out.println(commands.get("key"));

    StatefulRedisPubSubConnection<String, String> pubSubConnection = client.connectPubSub();

    pubSubConnection.addListener(new RedisPubSubAdapter<>());

    RedisPubSubCommands<String, String> pubSubCommands = pubSubConnection.sync();
    pubSubCommands.subscribe("channel");
    commands.publish("channel", "msg");


    client.shutdown();
    TimeUnit.SECONDS.sleep(10);
  }
}
