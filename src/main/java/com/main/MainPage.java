package com.main;
import com.entities.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import com.services.ProductsService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/index.jsp")
public class MainPage extends HttpServlet {
  private ProductsService productsService;

  public void init() throws ServletException {
    super.init();
    productsService = new ProductsService();
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    Product[] products = null;

    try {
      products = productsService.getList(req, resp);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    req.setAttribute("products", products);
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/store/store.html.jsp");
    view.forward(req, resp);
  }
}
