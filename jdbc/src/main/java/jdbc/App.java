package jdbc;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
  public static void main(String[] args) throws Exception {

    Class.forName(Driver.class.getName()); // TODO: looks like it is required now

    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test" +
        "?createDatabaseIfNotExist=true&user=root&password=sql567"
        + "&traceWithActiveSpanOnly=false");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("show tables;");

    while (rs.next()) {
      int columnCount = rs.getMetaData().getColumnCount();
      System.out.println(columnCount);
    }
    rs.close();
    stmt.close();
    conn.close();

    Thread.sleep(30_000L);
  }
}
