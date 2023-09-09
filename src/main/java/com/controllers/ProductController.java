package com.controllers;

import com.entities.store.data.User;
import com.entities.store.data.Cart;
import com.services.AuthService;
import com.services.BasketService;
import com.services.ProductsService;
import com.entities.store.data.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product")
public class ProductController extends HttpServlet {

    private ProductsService productsService;
    private AuthService authService;
    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        super.init();
        productsService = new ProductsService();
        authService = new AuthService();
        basketService = new BasketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user;
        Product product;
        Cart cart;
        try {
            product = productsService.getProduct(id);
            user = authService.getUserInfo(req);
            cart = basketService.getCart(user.getUserId());
            user.setText("Hello " + user.getLogin() + "!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("Product", product);
        req.setAttribute("user", user);
        req.setAttribute("cart", cart);
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/store/components/productDetailPage.html.jsp");
        view.forward(req, resp);
    }
}
