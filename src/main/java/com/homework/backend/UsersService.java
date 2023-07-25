package com.homework.backend;

import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.DriverManager;

public class UsersService extends HttpServlet {

  public Connection connectDB(String dbName, String user, String pass) {
    Connection conn = null;
    try {
      Class.forName("org.postgresql.Driver");
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, pass);
      if (conn != null) {
        System.out.println("Connection success");
      } else {
        System.out.println("Connction Failed");
      }
    } catch(Exception e) {
      System.out.println(e);
    }
    return conn;
  }

  public String Hello() {
    System.out.println("Hello");
    return "<h1>" + "реактивность" + "</h1>";
  }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        try {
//          String url = "jdbc:postgresql://localhost/secret?serverTimezone=Europe/Moscow&useSSL=false";
//          String username = "postgres";
//          String password = "1234";
//          Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
//          try (Connection conn = DriverManager.getConnection(url, username, password)){
//
//            writer.println("Connection to ProductDB succesfull!");
//          }
//        } catch (Exception ex) {
//          writer.println("Connection failed...");
//          writer.println(ex);
//        } finally {
//          writer.close();
//        }
//    }
}
