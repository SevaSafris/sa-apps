package jdbi;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    Class.forName("org.h2.Driver");

    final Jdbi jdbi = Jdbi.create("jdbc:h2:mem:jdbc");

    Handle handle = jdbi.open();
    handle.execute("CREATE TABLE accounts (id BIGINT AUTO_INCREMENT, PRIMARY KEY (id))");
    handle.execute("DROP TABLE accounts");

    handle.close();

    Util.checkSpan("java-jdbc", 3);
  }
}
