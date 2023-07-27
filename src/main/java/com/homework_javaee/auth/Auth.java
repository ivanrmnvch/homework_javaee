package com.homework_javaee.auth;

import com.homework.backend.UsersService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/sign-in")
public class Auth extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            usersService = new UsersService("secret", "postgres", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("auth/auth.html.jsp");
        System.out.println(view);
        view.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("user name: " + userName);

        try {
            boolean isIdent = usersService.Identification(userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        writer.println("<h1>" + "sign-in" + userName + password + "</h1>");
        writer.close();
    }
}
