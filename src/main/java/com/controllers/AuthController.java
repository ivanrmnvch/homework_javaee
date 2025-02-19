package com.controllers;

import com.services.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({
  "/auth",
  "/auth-form",
  "/logout",
})
public class AuthController extends HttpServlet {
  private AuthService authService;
  @Override
  public void init() throws ServletException {
    super.init();
    authService = new AuthService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String uri = req.getServletPath();
    try {
      if ("/auth-form".equals(uri)) {
        authService.getAuthForm(req,resp);
      } else if ("/auth".equals(uri)) {
        authService.login(req, resp);
      } else if ("/logout".equals(uri)) {
        authService.logout(req, resp);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}
}