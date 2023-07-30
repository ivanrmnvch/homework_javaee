package com.utils;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.*;
public class DatabaseUtils {
  private static DataSource dataSource;
  private static DatabaseUtils instance;

  private DatabaseUtils() {}

  public static synchronized DatabaseUtils getInstance() {
    if (instance == null) {
      instance = new DatabaseUtils();
      initializeDataSource();
    }
    return instance;
  }

  private static void initializeDataSource() {
    try {
      Context ctx = new InitialContext();
      dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/SecretDB");
    } catch (NamingException e) {
      System.err.println("ERROR CONNECTING TO DATABASE::" + e.getMessage().replace("ERROR: ", ""));
    }
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}

