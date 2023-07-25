package com.homework.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@WebServlet("/users")
public class Users extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DO GET !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        try {
          String url = "jdbc:postgresql://localhost/secret?serverTimezone=Europe/Moscow&useSSL=false";
          String username = "postgres";
          String password = "1234";
          Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
          try (Connection conn = DriverManager.getConnection(url, username, password)){

            writer.println("Connection to ProductDB succesfull!");
          }
        } catch (Exception ex) {
          writer.println("Connection failed...");
          writer.println(ex);
        } finally {
          writer.close();
        }
    }
}
