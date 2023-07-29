package com.homework_javaee.controller;

import com.homework.backend.RegistrationService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({
    "/registration",
})
public class RegistrationController extends HttpServlet {
    private RegistrationService registrationService;
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            registrationService = new RegistrationService("secret", "postgres", "1234");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getServletPath();
        if ("/registration".equals(uri)) {
            registrationService.getOne(req, resp);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            registrationService = new RegistrationService("secret", "postgres", "1234");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String email = req.getParameter("email");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        writer.println("<h1>" + "registration" + userName + password + "</h1>");
        writer.close();
    }
}