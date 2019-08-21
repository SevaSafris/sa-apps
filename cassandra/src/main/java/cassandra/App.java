package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    EmbeddedCassandraServerHelper.startEmbeddedCassandra();
    EmbeddedCassandraServerHelper.getSession();

    Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9142).build();
    Session session = null;
    try {
      session = cluster.connect();
    } catch (Exception e) {
      System.err.println("Cannot connect, exiting.\n" + e.getMessage());
      System.exit(-1);
    }

    ResultSet resultSet = session.execute("select * from system.compaction_history");
    System.out.println("rows: " + resultSet.all().size());

    session.close();
    cluster.close();

    EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();

    Util.checkSpan("java-cassandra", 1);

    System.exit(0);
  }


}
