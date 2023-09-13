//package com.controllers;
//
//import com.entities.store.data.Cart;
//import com.entities.store.data.Product;
//import com.entities.store.data.User;
//import com.services.AuthService;
//import com.services.BasketService;
//import com.services.ProductsService;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/product/search")
//public class SearchController extends HttpServlet {
//
//    private ProductsService productsService;
//    private AuthService authService;
//    private BasketService basketService;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        productsService = new ProductsService();
//        authService = new AuthService();
//        basketService = new BasketService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user;
//        Product product;
//        Cart cart;
//
//        try {
//            user = authService.getUserInfo(req);
//            cart = basketService.getCart(user.getUserId());
//            product = productsService.productSearch(req, resp);
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        req.setAttribute("user", user);
//        req.setAttribute("cart", cart);
//        req.setAttribute("product", product);
//        String path = "/edit";
//        ServletContext servletContext = getServletContext();
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
//        requestDispatcher.forward(req, resp);
//    }
//}
