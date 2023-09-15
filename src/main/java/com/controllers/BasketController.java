package com.controllers;

import com.entities.store.data.Cart;
import com.entities.store.data.User;
import com.services.AuthService;
import com.services.BasketService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet({
    "/basket",
    "/basket/add",
    "/basket/delete",
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String productId = req.getParameter("productId");
        String uri = req.getServletPath();
        User user;
        try {
            user = authService.getUserInfo(req);
            if (!Objects.equals(user.getRole(), "admin")) {
                String path = req.getContextPath() + "/products";
                resp.sendRedirect(path);
                return;
            }

            String userId = user.getUserId();
            if ("/basket".equals(uri)) {
                Cart cart = basketService.getCart(userId);
                req.setAttribute("cart", cart);
                req.setAttribute("user", user);
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/cart/pages/cart.html.jsp");
                view.forward(req, resp);
            } else if ("/basket/add".equals(uri)) {
                basketService.addProduct(userId, productId);
                String path = req.getContextPath() + "/product?id=" + productId;
                resp.sendRedirect(path);
            } else if ("/basket/delete".equals(uri)) {
                basketService.deleteProduct(userId, productId);
                String path = req.getContextPath() + "/basket?id=" + productId;
                resp.sendRedirect(path);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
