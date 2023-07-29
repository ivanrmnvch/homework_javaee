package com.homework.backend;

import jakarta.servlet.http.HttpServlet;

import java.sql.*;
import java.util.Objects;

public class AuthService extends HttpServlet {

  private Statement service = null;

  public AuthService(String dbName, String user, String pass) throws Exception {
    super.init();
    Connection conn;
    String isConnected = "f";
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, pass);
      this.service = conn.createStatement();
      ResultSet res = service.executeQuery("SELECT \"isConnected\" FROM connection_test");

      while (res.next()) {
        isConnected = res.getString("isConnected");
      }

      if (Objects.equals(isConnected, "t")) {
        System.out.println("Connection success");
      } else {
        System.err.println("Connection Failed");
      }
      res.close();
      service.close();
      conn.close();
    } catch(Exception e) {
      System.err.println("ERROR CONNECTING TO DATABASE::" + e.getMessage().replace("ERROR: ", ""));
    }
  }

  public boolean Identification(String identifier) throws SQLException {
    ResultSet res;
//    try {
//      res = service.executeQuery("" +
//              "DO " +
//                "$do$ " +
//                  "BEGIN " +
//                    "IF EXISTS (SELECT id FROM user " +
//                               "WHERE emeil = " + "'" + identifier + "'" +
//                      "");
//    } catch {
//
//    }
    try {
      res = service.executeQuery("" +
      "SELECT " +
        "email," +
        "blocked" +
      " FROM users" +
      " WHERE email = " + "'" + identifier + "'"
      );
    } catch (Exception e) {
      System.err.println("ERROR IDENTIFICATION::" + e.getMessage().replace("ERROR: ", ""));
      return false;
    }

    boolean identiti = false;
    boolean isBlocked = false;

    while (res.next()) {
      String email = res.getString("email");
      identiti = Objects.equals(email, identifier);
      String blocked = res.getString("blocked");
      isBlocked = Objects.equals(blocked, "t");
    }
    return identiti && !isBlocked;
  }

  public boolean Authentication(String pass) throws SQLException {
    ResultSet res;
    try {
      res = service.executeQuery("" +
        "SELECT " +
          "pass" +
        " FROM users" +
        " WHERE pass =" + "''" + pass + "'';"
      );
    } catch (Exception e) {
      System.err.println("ERROR AUTHENTICATION::" + e.getMessage().replace("ERROR: ", ""));
      return false;
    }

    System.out.println("res: " + res);

    boolean authenticated = false;

    while (res.next()) {
      String ps = res.getString("pass");
      authenticated = Objects.equals(pass, ps);
    }
    return authenticated;
  }
}
