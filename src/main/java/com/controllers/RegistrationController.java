package com.controllers;

import com.services.RegistrationService;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({
  "/registration",
  "/registration-form",
})
public class RegistrationController extends HttpServlet {
  private RegistrationService registrationService;
  @Override
  public void init() throws ServletException {
    super.init();
    registrationService = new RegistrationService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String uri = req.getServletPath();
    if ("/registration-form".equals(uri)) {
      registrationService.getRegistrationForm(req, resp);
    } else if ("/registration".equals(uri)) {

    }
  }
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    String uri = req.getServletPath();

    try {
      if ("/registration".equals(uri)) {
        registrationService.createUser(req, resp);
      }
    } catch (SQLException | NamingException e) {
      throw new RuntimeException(e);
    }

//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//
//        String email = req.getParameter("email");
//        String userName = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        writer.println("<h1>" + "registration" + userName + password + "</h1>");
//        writer.close();
  }
}