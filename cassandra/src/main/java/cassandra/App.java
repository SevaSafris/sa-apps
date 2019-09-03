package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import java.io.File;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    if(!System.getProperty("java.version").startsWith("1.8.")) {
      System.exit(0);
    }

    if (System.getProperty("user.dir").contains("cassandra")) {
      System.getProperties().setProperty("java.library.path", "src/main/resources/libs");
    } else {
      System.getProperties().setProperty("java.library.path", "cassandra/src/main/resources/libs");
    }

    final File triggers = new File("triggers");
    triggers.mkdirs();
    System.setProperty("cassandra.triggers_dir", triggers.getAbsolutePath());

    EmbeddedCassandraServerHelper.startEmbeddedCassandra(60_000);
    // EmbeddedCassandraServerHelper.getSession();

    Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9142).build();
    Session session = null;
    try {
      session = cluster.connect();
    } catch (Exception e) {
      System.err.println("ERROR: Cannot connect, exiting.\n" + e.getMessage());
      System.exit(-1);
    }

    ResultSet resultSet = session.execute("select * from system.compaction_history");
    System.out.println("rows: " + resultSet.all().size());

    session.close();
    cluster.close();

    Util.checkSpan("java-cassandra", 1);

    EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    System.exit(0);
  }


}
