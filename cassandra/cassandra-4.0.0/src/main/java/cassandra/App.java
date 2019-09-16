package cassandra;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.internal.core.metadata.DefaultEndPoint;
import java.io.File;
import java.net.InetSocketAddress;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    if (!System.getProperty("java.version").startsWith("1.8.")) {
      System.exit(0);
    }

    if (System.getProperty("user.dir").contains("cassandra")) {
      System.getProperties().setProperty("java.library.path", "src/main/resources/libs");
    } else {
      System.getProperties().setProperty("java.library.path", "cassandra/src/main/resources/libs");
    }

    final File triggers = new File("target/triggers");
    triggers.mkdirs();
    System.setProperty("cassandra.triggers_dir", triggers.getAbsolutePath());

    EmbeddedCassandraServerHelper.startEmbeddedCassandra(60_000);
    // EmbeddedCassandraServerHelper.getSession();

    CqlSession session = CqlSession.builder()
        .addContactEndPoint(new DefaultEndPoint(new InetSocketAddress("127.0.0.1", 9142)))
        .withLocalDatacenter("datacenter1")
        .build();

    ResultSet resultSet = session.execute("select * from system.compaction_history");
    System.out.println("rows: " + resultSet.all().size());

    session.close();

    Util.checkSpan("java-cassandra", 1);

    EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    System.exit(0);
  }


}
