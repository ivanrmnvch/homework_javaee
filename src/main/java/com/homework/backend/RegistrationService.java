package com.homework.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class RegistrationService  extends HttpServlet {

  private Statement service = null;

  public RegistrationService(String dbName, String user, String pass) throws Exception {
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
    } catch(Exception e) {
      System.err.println("ERROR CONNECTING TO DATABASE::" + e.getMessage().replace("ERROR: ", ""));
    }
  }

  public void getOne(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("test");
//    RequestDispatcher view = req.getRequestDispatcher("../../main/webapp/auth/registration.html.jsp");
    RequestDispatcher view = req.getRequestDispatcher("auth/registration.html.jsp");
    System.out.println(view);
    view.forward(req, resp);;
  }

//  public boolean Registration (String ) {
//
//  }
}
