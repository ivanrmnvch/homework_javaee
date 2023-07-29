package com.services;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class RegistrationService  extends HttpServlet {
  @Resource(name="jdbc/SecretDB")
  private DataSource dataSource;
  public void getOne(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher view = req.getRequestDispatcher("auth/registration.html.jsp");
    view.forward(req, resp);;
  }

  public void createUser(HttpServletRequest req,  HttpServletResponse resp) {
    String email = req.getParameter("email");
    String userName = req.getParameter("username");
    String password = req.getParameter("password");

    System.out.println(email + " " + userName + " " + password);
  }
}

