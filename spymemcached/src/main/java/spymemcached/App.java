package spymemcached;

import java.net.InetSocketAddress;
import net.spy.memcached.MemcachedClient;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    MemcachedClient client = new MemcachedClient(new InetSocketAddress("localhost", 11211));

    System.out.println("set=" + client.set("key", 20, "value").get());

    System.out.println("get value=" + client.get("key"));

    client.shutdown();

    Util.checkSpan("java-memcached", 2);
  }
}
