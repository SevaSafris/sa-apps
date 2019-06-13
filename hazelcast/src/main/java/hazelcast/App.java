package hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import java.util.concurrent.TimeUnit;

public class App {
  public static void main(String[] args) throws InterruptedException {
    HazelcastInstance instance = Hazelcast.newHazelcastInstance(new Config());
    final IMap<String, String> map = instance.getMap("map");
    map.put("key", "value");
    System.out.println("value=" + map.get("key"));

    instance.shutdown();

    TimeUnit.SECONDS.sleep(10);
  }
}
