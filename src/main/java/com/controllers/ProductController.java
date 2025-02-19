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
import java.util.Objects;

@WebServlet({
    "/product",
    "/product/create",
    "/product/update",
})
public class ProductController extends HttpServlet {

    private ProductsService productsService;
    private AuthService authService;
    private BasketService basketService;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }
    @Override
    public void init() throws ServletException {
        super.init();
        productsService = new ProductsService();
        authService = new AuthService();
        basketService = new BasketService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PRODUCT");
        String uri = req.getServletPath();
        String id = req.getParameter("id");
        User user;
        Product product;
        Cart cart;
        try {
            user = authService.getUserInfo(req);

            if ("/product".equals(uri)) {
                product = productsService.getProduct(id);
                cart = basketService.getCart(user.getUserId());
                req.setAttribute("Product", product);
                req.setAttribute("user", user);
                req.setAttribute("cart", cart);
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/store/components/productDetailPage.html.jsp");
                view.forward(req, resp);
            }

            if (!Objects.equals(user.getRole(), "admin")) {
                RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/error/pages/error.html.jsp");
                view.forward(req, resp);
                return;
            }

            if ("/product/create".equals(uri)) {
                boolean success = productsService.addProduct(req, resp);
                String path = success ? "/create-success" : "/create-error";
                resp.sendRedirect(req.getContextPath() + path);
            } else if ("/product/update".equals(uri)) {
                boolean success = productsService.productUpdate(req, resp);
                String path = success ? "/edit-success" : "/edit-error";
                resp.sendRedirect(req.getContextPath() + path);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
