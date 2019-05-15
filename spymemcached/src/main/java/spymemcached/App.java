package spymemcached;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import net.spy.memcached.MemcachedClient;

public class App {
  public static void main(String[] args) throws Exception {
    MemcachedClient client = new MemcachedClient(new InetSocketAddress("localhost", 11211));

    System.out.println("set=" + client.set("key", 20, "value").get());

    System.out.println("get value=" + client.get("key"));

    client.shutdown();

    TimeUnit.SECONDS.sleep(30);
  }
}
