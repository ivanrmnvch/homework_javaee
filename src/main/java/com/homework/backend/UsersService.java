package com.homework.backend;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import java.sql.*;
import java.util.Objects;

public class UsersService extends HttpServlet {

  private Statement service = null;

  public UsersService(String dbName, String user, String pass) throws ServletException, SQLException {
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
        System.out.println("Connection Failed");
      }
    } catch(Exception e) {
      System.err.println("ERROR CONNECTING TO DATABASE::" + e);
    }
  }

  public boolean Identification(String identifier) throws SQLException {
    ResultSet res;
    try {
      res = service.executeQuery("" +
      "SELECT " +
        "id," +
        "blocked" +
      " FROM users" +
      " WHERE email = " + "'" + identifier + "'"
      );
    } catch (Exception e) {
      System.err.println("ERROR IDENTIFICATION::" + e);
      return false;
    }

    boolean identiti = false;
    boolean isBlocked = false;

    while (res.next()) {
      String id = res.getString("id");
      if (Integer.parseInt(id) > 0) {
        identiti = true;
      }
      String blocked = res.getString("blocked");
      isBlocked = Objects.equals(blocked, "t");
    }
    return identiti && !isBlocked;
  }
}
