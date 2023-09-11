package com.controllers;
import com.entities.store.data.CreatePage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

// todo сделать отображение hello userName в header
// todo сделать форму ошибки логина

@WebServlet({
  "/index.jsp",
  "/create",
})
public class PageController extends HttpServlet {
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
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}
}
