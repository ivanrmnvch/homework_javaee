package com.controllers;

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

    @Override
    public void init() throws ServletException {
        super.init();
        productsService = new ProductsService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product product;
        try {
            product = productsService.getProduct(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("Product", product);
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/store/components/productDetailPage.html.jsp");
        view.forward(req, resp);
    }
}
