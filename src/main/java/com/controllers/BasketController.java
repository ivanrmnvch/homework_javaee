package com.controllers;

import com.entities.store.data.User;
import com.services.AuthService;
import com.services.BasketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({
    "/basket",
})
public class BasketController extends HttpServlet {
    private AuthService authService;
    private BasketService basketService;
    @Override
    public void init() throws ServletException {
        super.init();
        authService = new AuthService();
        basketService = new BasketService();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String productId = req.getParameter("productId");
        User user;
        try {
            user = authService.getUserInfo(req);
            basketService.addProduct(user.getUserId(), productId);
            System.out.println("PRODUCT ID " + productId);
            String path = req.getContextPath() + "/product?id=" + productId;
            resp.sendRedirect(path);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
