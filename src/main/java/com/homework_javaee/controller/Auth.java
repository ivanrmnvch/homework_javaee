package com.homework_javaee.controller;

import com.homework.backend.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/auth")
public class Auth extends HttpServlet {
    private AuthService authService;
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            authService = new AuthService("secret", "postgres", "1234");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            boolean isIdent = authService.Identification(userName);
            boolean isAuth = authService.Authentication(password);
            if (isIdent && isAuth) {
                System.out.println("auth::TRUE");
                //RequestDispatcher view = req.getRequestDispatcher("store/index.jsp");
                //System.out.println(view);
                //view.forward(req, resp);
            } else {
                System.out.println("auth::FALSE");
                // warning
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        writer.println("<h1>" + "sign-in" + userName + password + "</h1>");
        writer.close();

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
