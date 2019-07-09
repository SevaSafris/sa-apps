package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import java.util.concurrent.TimeUnit;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {

    Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9042).build();
    Session session;
    try {
      session = cluster.connect();
    } catch (Exception e) {
      System.err.println("Cannot connect, exiting.\n" + e.getMessage());
      return;
    }

    ResultSet resultSet = session.execute("select * from system.compaction_history");
    System.out.println("rows: " + resultSet.all().size());

    session.close();
    cluster.close();

    TimeUnit.SECONDS.sleep(10);

    Util.checkSpan("java-cassandra", 1);
  }


}
