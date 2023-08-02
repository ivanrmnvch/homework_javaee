package com.services;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.DbUtils;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;

import com.utils.DatabaseUtils;

public class RegistrationService extends HttpServlet {
  public void getOne(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/auth/auth-form.html.jsp");
    view.forward(req, resp);
  }

  public void createUser(HttpServletRequest req,  HttpServletResponse resp) throws SQLException, NamingException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();

    String email = req.getParameter("email");
    String userName = req.getParameter("username");
    String password = req.getParameter("password");

    Statement service = connection.createStatement();
    String sql = String.format("" +
      "INSERT INTO users (login, pass, email, \"fName\", \"lName\", blocked, updated, created) " +
      "VALUES ('%s', '%s', '%s', '%s', '%s', false, now()::timestamp, now()::timestamp);",
      userName, password, email, "unknown", "unknown"
    );

    boolean rs;

    try {
      rs = service.execute(sql);
    } catch (Exception e) {
      System.out.println("USER REGISTRATION ERROR::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection);
      DbUtils.closeQuietly(service);
    }

    System.out.println(email + " " + userName + " " + password);
  }
}

