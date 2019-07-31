package jdbi;

import java.util.concurrent.TimeUnit;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public class App {
  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");

    final Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost/test" +
        "?createDatabaseIfNotExist=true&user=travis");

    Handle handle = jdbi.open();
    handle.execute("CREATE TABLE accounts (id BIGINT AUTO_INCREMENT, PRIMARY KEY (id))");
    handle.execute("DROP TABLE accounts");

    handle.close();

    TimeUnit.SECONDS.sleep(10);
  }
}
