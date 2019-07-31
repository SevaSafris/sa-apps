package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import util.Util;

public class App {
  public static void main(String[] args) throws Exception {
    Class.forName("org.h2.Driver");

    Connection conn = DriverManager.getConnection("jdbc:h2:mem:jdbc");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("show databases;");

    while (rs.next()) {
      System.out.println(rs.getString(1));
    }
    rs.close();
    stmt.close();
    conn.close();

    Util.checkSpan("java-jdbc", 2);
  }
}
