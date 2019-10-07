package lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisCommandExecutionException;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import redis.embedded.RedisServer;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    final RedisServer server = new RedisServer();
    server.start();

    RedisClient client = RedisClient.create("redis://localhost");
    StatefulRedisConnection<String, String> connection = client.connect();
    RedisCommands<String, String> commands = connection.sync();
    System.out.println(commands.set("key", "value"));
    System.out.println(commands.get("key"));
    try {
      System.out.println("memory usage: " + commands.memoryUsage("key"));
    } catch (RedisCommandExecutionException ignore) {
    }

    StatefulRedisPubSubConnection<String, String> pubSubConnection = client.connectPubSub();

    pubSubConnection.addListener(new RedisPubSubAdapter<>());

    RedisPubSubCommands<String, String> pubSubCommands = pubSubConnection.sync();
    pubSubCommands.subscribe("channel");
    commands.publish("channel", "msg");

    Thread.sleep(5_000); // sleep to wait for 7 spans
    client.shutdown();

    server.stop();

    Util.checkSpan("java-redis", 7);
    System.exit(0);
  }
}
