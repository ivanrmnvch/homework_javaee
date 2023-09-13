package com.controllers;
import com.entities.store.data.*;
import com.services.AuthService;
import com.services.BasketService;
import com.services.ProductsService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.SQLException;

// todo сделать отображение hello userName в header
// todo сделать форму ошибки логина

@WebServlet({
        "/index.jsp",
        "/create",
        "/edit",
        "/edit-update",
})
public class PageController extends HttpServlet {
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
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String uri = req.getServletPath();
    if ("/index.jsp".equals(uri)) {
      String path = "/products";
      ServletContext servletContext = getServletContext();
      RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
      requestDispatcher.forward(req, resp);
    } else if ("/create".equals(uri)) {
      req.setAttribute("page", new CreatePage());
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/create/pages/create-page.html.jsp");
      view.forward(req, resp);
    } else if ("/edit".equals(uri)) {
      Product product = new Product();
      User user = new User();
      Cart cart = new Cart();
      try {
        product = productsService.productSearch(req, resp);
        user = authService.getUserInfo(req);
        cart = basketService.getCart(user.getUserId());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      req.setAttribute("user", user);
      req.setAttribute("cart", cart);
      req.setAttribute("product", product);
      req.setAttribute("page", new CreatePage());
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/pages/edit-page.html.jsp");
      view.forward(req, resp);
    } else if ("/edit-update".equals(uri)) {
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/components/product-update-page.html.jsp");
      System.out.println("VIEW " + view);
      view.forward(req, resp);
    }
  }
}
