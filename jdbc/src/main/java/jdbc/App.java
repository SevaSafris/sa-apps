package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
  public static void main(String[] args) throws Exception {

    //Class.forName(Driver.class.getName());

    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test" +
        "?createDatabaseIfNotExist=true&user=travis"
        + "&traceWithActiveSpanOnly=false");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("show databases;");

    while (rs.next()) {
      System.out.println(rs.getString(1));
    }
    rs.close();
    stmt.close();
    conn.close();

    Thread.sleep(30_000L);
  }
}
