package com.controllers;
import com.entities.store.data.*;
import com.responses.products.search.Response;
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
import java.util.Objects;

// todo вынести все стили в index.css
// todo сделать форму ошибки логина (компонент уже готов)
// todo возможная ошибка получения корзины в header
// todo выровнять фильтр

@WebServlet({
        "/index.jsp",
        "/create",
        "/edit",
        "/edit-success",
        "/edit-error",
        "/create-success",
        "/create-error",
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
      return;
    }

    User user;
    try {
      user = authService.getUserInfo(req);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    if (!Objects.equals(user.getRole(), "admin")) {
      String path = req.getContextPath() + "/products";
      resp.sendRedirect(path);
      return;
    }

    if ("/create".equals(uri)) {
      req.setAttribute("page", new CreatePage());
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/create/pages/create-page.html.jsp");
      view.forward(req, resp);
    } else if ("/edit".equals(uri)) {
      Response response;
      Cart cart = new Cart();
      try {
        response = productsService.productSearch(req, resp);
        if (response.getSuccess()) {
          cart = basketService.getCart(user.getUserId());
        } else {
          req.setAttribute("message", response.getMessage());
          req.setAttribute("style", "red");
          RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/components/product-update-success.html.jsp");
          view.forward(req, resp);
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      req.setAttribute("user", user);
      req.setAttribute("cart", cart);
      req.setAttribute("product", response.getProduct());
      req.setAttribute("page", new CreatePage());
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/pages/edit-page.html.jsp");
      view.forward(req, resp);
    } else if ("/edit-success".equals(uri)) {
      String message = "Товар успешно изменен!";
      String style = "green";
      req.setAttribute("message", message);
      req.setAttribute("style", style);
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/components/product-update-success.html.jsp");
      view.forward(req, resp);
    } else if ("/edit-error".equals(uri)) {
      String message = "Ошибка редактирования товара!";
      String style = "red";
      req.setAttribute("message", message);
      req.setAttribute("style", style);
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/components/product-update-success.html.jsp");
      view.forward(req, resp);
    } else if ("/create-success".equals(uri)) {
      String message = "Товар успешно создан!";
      String style = "green";
      req.setAttribute("message", message);
      req.setAttribute("style", style);
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/components/product-update-success.html.jsp");
      view.forward(req, resp);
    } else if ("/create-error".equals(uri)) {
      String message = "Ошибка создания товара!";
      String style = "red";
      req.setAttribute("message", message);
      req.setAttribute("style", style);
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/components/product-update-success.html.jsp");
      view.forward(req, resp);
    } else if ("/login-error".equals(uri)) {

    }
  }
}
