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

@WebServlet({
        "/index.jsp",
        "/create",
        "/edit",
        "/edit-success",
        "/edit-error",
        "/create-success",
        "/create-error",
        "/login-error",
})
public class PageController extends HttpServlet {
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
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String uri = req.getServletPath();
    if ("/index.jsp".equals(uri)) {
      String path = "/products";
      ServletContext servletContext = getServletContext();
      RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
      requestDispatcher.forward(req, resp);
      return;
    } else if ("/login-error".equals(uri)) {
      req.setAttribute("action", "auth-form");
      req.setAttribute("btnText", "Попробовать ещё");
      req.setAttribute("message", "Ошибка авторизации!");
      RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/ui/notifications/message-notification.html.jsp");
      view.forward(req, resp);
      return;
    }

    User user;
    Cart cart;
    try {
      user = authService.getUserInfo(req);

      if (!Objects.equals(user.getRole(), "admin")) {
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/error/pages/error.html.jsp");
        view.forward(req, resp);
        return;
      }

      cart = basketService.getCart(user.getUserId());
      req.setAttribute("user", user);
      req.setAttribute("cart", cart);

      if ("/create".equals(uri)) {
        req.setAttribute("page", new CreatePage());
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/create/pages/create-page.html.jsp");
        view.forward(req, resp);
      } else if ("/edit".equals(uri)) {
        Response response;
        response = productsService.productSearch(req, resp);
        if (!response.getSuccess()) {
          getNotificationPage(
            req,
            resp,
            response.getMessage(),
            "red"
          );
        }
        req.setAttribute("product", response.getProduct());
        req.setAttribute("page", new CreatePage());
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/edit/pages/edit-page.html.jsp");
        view.forward(req, resp);
      } else if ("/edit-success".equals(uri)) {
        getNotificationPage(
          req,
          resp,
          "Товар успешно изменен!",
          "green"
        );
      } else if ("/edit-error".equals(uri)) {
        getNotificationPage(
          req,
          resp,
          "Ошибка редактирования товара!",
          "red"
        );
      } else if ("/create-success".equals(uri)) {
        getNotificationPage(
          req,
          resp,
          "Товар успешно создан!",
          "green"
        );
      } else if ("/create-error".equals(uri)) {
        getNotificationPage(
          req,
          resp,
          "Ошибка создания товара!",
          "red"
        );
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void getNotificationPage(
    HttpServletRequest req,
    HttpServletResponse resp,
    String message,
    String style
  ) throws IOException, ServletException {
    req.setAttribute("message", message);
    req.setAttribute("style", style);
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/ui/notifications/edit-notification.html.jsp");
    view.forward(req, resp);
  }
}
