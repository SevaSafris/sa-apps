package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

public class App {
  public static void main(String[] args) throws Exception {

    Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9042).build();
    Session session = cluster.connect();
    System.out.println(session);

    long start = System.currentTimeMillis();
    for (int i = 0; i < 10; i++) {
      ResultSet resultSet = session.execute("select * from system.compaction_history");
      System.out.println("rows: " + resultSet.all().size());
    }
    long end = System.currentTimeMillis();

    double duration = (end - start) / 1000.0;
    System.out.println("Duration: " + duration + " sec");

    session.close();
    cluster.close();

    Thread.sleep(30_000L);

  }


}
