package com.services;

import com.utils.DatabaseUtils;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.DbUtils;

public class AuthService extends HttpServlet {
  public void Login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
    String userName = req.getParameter("username");
    String password = req.getParameter("password");

    boolean isIdent = Identification(userName);
    boolean isAuth = Authentication(password);

    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    if (isIdent && isAuth) {
      writer.println("<h3>" + "Authorization was successful" + "</h3>");
      writer.println("<p>" + "Hello " + userName + "!</p>");
    } else {
      writer.println("<h3>" + "Authorization failed" + "</h3>");
      writer.println("<p>" + ":(" + "</p>");
    }
    writer.close();
  }

  private boolean Identification(String identifier) throws SQLException {
    DatabaseUtils databaseUtils = com.utils.DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement();
    ResultSet rs = null;

    boolean identiti = false;
    boolean isBlocked = false;

    try {
      rs = service.executeQuery("" +
      "SELECT " +
        "email," +
        "blocked" +
      " FROM users" +
      " WHERE email = " + "'" + identifier + "'"
      );

      while (rs.next()) {
        String email = rs.getString("email");
        identiti = Objects.equals(email, identifier);
        String blocked = rs.getString("blocked");
        isBlocked = Objects.equals(blocked, "t");
      }
    } catch (Exception e) {
      System.err.println("ERROR IDENTIFICATION::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
    return identiti && !isBlocked;
  }

  private boolean Authentication(String password) throws SQLException {
    DatabaseUtils databaseUtils = com.utils.DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement();
    ResultSet rs = null;

    boolean authenticated = false;

    try {
      rs = service.executeQuery("" +
        "SELECT " +
          "pass" +
        " FROM users" +
        " WHERE pass =" + "'" + password + "';"
      );

      while (rs.next()) {
        String ps = rs.getString("pass");
        authenticated = Objects.equals(password, ps);
      }
    } catch (Exception e) {
      System.err.println("ERROR AUTHENTICATION::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
    return authenticated;
  }
}





//private Statement service = null;

//  public AuthService(String dbName, String user, String pass) throws Exception {
//    super.init();
//    Connection conn;
//    String isConnected = "f";
//    try {
//      Class.forName("org.postgresql.Driver");
//      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, pass);
//      //conn = dataSource.getConnection();
//      this.service = conn.createStatement();
//      ResultSet res = service.executeQuery("SELECT \"isConnected\" FROM connection_test");
//
//      while (res.next()) {
//        isConnected = res.getString("isConnected");
//      }
//      System.out.println(55);
//      if (Objects.equals(isConnected, "t")) {
//        System.out.println("Connection success");
//      } else {
//        System.err.println("Connection Failed");
//      }
//      res.close();
//      service.close();
//      conn.close();
//    } catch(Exception e) {
//      System.err.println("ERROR CONNECTING TO DATABASE::" + e.getMessage().replace("ERROR: ", ""));
//    }
//  }

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
