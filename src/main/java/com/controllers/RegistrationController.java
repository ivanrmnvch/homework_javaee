package com.controllers;

import com.services.RegistrationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
  "/registration",
  "/registration-form",
  "/registration-success",
  "/registration-error",
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
    } else if ("/registration-success".equals(uri)) {
      registrationService.getRegistrationFormSuccess(req, resp);
    } else if ("/registration-error".equals(uri)) {
      registrationService.getRegistrationFormError(req, resp);
    }
  }
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String uri = req.getServletPath();
    try {
      if ("/registration".equals(uri)) {
        registrationService.createUser(req, resp);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}