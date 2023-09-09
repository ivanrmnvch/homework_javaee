package com.services;

import com.entities.store.data.User;
import com.utils.DatabaseUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.DbUtils;

public class AuthService extends HttpServlet {
  public void getAuthForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/auth/auth-form.html.jsp");
    view.forward(req, resp);
  }

  public void login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
    String userName = req.getParameter("username");
    String password = req.getParameter("password");

    boolean isIdent = identification(userName);
    boolean isAuth = authentication(password);

    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    if (isIdent && isAuth) {
      resp.addCookie(new Cookie("user", userName));
      //resp.addCookie(new Cookie("hello", getUserLogin(userName)));
    }
    String path = req.getContextPath() + "/products";
    resp.sendRedirect(path);
    writer.close();
  }

  public void logout(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
    Cookie cookie = new Cookie("user", "");
    cookie.setMaxAge(0);
    resp.addCookie(cookie);

    String path = req.getContextPath() + "/products";
    resp.sendRedirect(path);
  }

  private boolean identification(String identifier) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
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

  private boolean authentication(String password) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
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

  public User getUserInfo(HttpServletRequest req) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement();
    ResultSet rs = null;

    String email = "";
    Cookie[] cookies = req.getCookies();
    for (Cookie c: cookies) {
      if ("user".equals(c.getName())) {
        email = c.getValue();
        break;
      }
    }

    if (Objects.equals(email, "")) {
      return new User();
    }

    try {
      rs = service.executeQuery("" +
        "SELECT " +
          "id," +
          "login" +
        " FROM users" +
        " WHERE email =" + "'" + email + "';"
      );

      if (rs.next()) {
        System.out.println("ID 1111 " + rs.getString("id"));
        return new User(
          rs.getString("id"),
          rs.getString("login"),
          true
        );
      } else {
        return new User();
      }
    } catch (Exception e) {
      System.err.println("LOGOUT ERROR::" + e.getMessage().replace("ERROR: ", ""));
      return new User();
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
  }
}