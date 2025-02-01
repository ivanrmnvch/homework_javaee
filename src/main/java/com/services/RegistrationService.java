package com.services;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.DbUtils;

import java.io.IOException;
import java.sql.*;

import com.utils.DatabaseUtils;

public class RegistrationService extends HttpServlet {
  public void getRegistrationForm(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/auth/registration-form.html.jsp");
    view.forward(req, resp);
  }

  public void getRegistrationFormSuccess(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/auth/registration-form-success.html.jsp");
    view.forward(req, resp);
  }

  public void getRegistrationFormError(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/auth/registration-form-error.html.jsp");
    view.forward(req, resp);
  }

  public void createUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    );

    String email = req.getParameter("email");
    String userName = req.getParameter("username");
    String password = req.getParameter("password");

    boolean userExists = checkUser(service, email);

    if (userExists) {
      String path = req.getContextPath() + "/registration-error";
      resp.sendRedirect(path);
      return;
    }

    String sql = String.format("" +
      "INSERT INTO users (login, pass, email, \"fName\", \"lName\", blocked, updated, created) " +
      "VALUES ('%s', '%s', '%s', '%s', '%s', false, now()::timestamp, now()::timestamp);",
      userName, password, email, "unknown", "unknown"
    );

    try {
      service.execute(sql);
    } catch (Exception e) {
      System.out.println("USER REGISTRATION ERROR::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection);
      DbUtils.closeQuietly(service);
    }
    String path = req.getContextPath() + "/registration-success";
    resp.sendRedirect(path);
  }

  private boolean checkUser(Statement service, String email) {
    String sql = String.format("SELECT 1 FROM users WHERE email = '%s'", email);
    int count;
    try {
       ResultSet rs = service.executeQuery(sql);
       rs.afterLast();
       rs.previous();
       count = rs.getRow();
       return count != 0;
    } catch (Exception e) {
      System.out.println("USER VALIDATION ERROR::" + e.getMessage().replace("ERROR: ", ""));
    }
    return false;
  }
}

