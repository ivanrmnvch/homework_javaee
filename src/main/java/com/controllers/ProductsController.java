package com.controllers;

import com.entities.store.StoreData;
import com.responses.products.Response;
import com.services.ProductsService;
import com.entities.common.TableMeta;
import com.state.store.Store;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({
  "/products",
})
public class ProductsController extends HttpServlet {
  @EJB
  private Store store;
  private ProductsService productsService;
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    super.service(req, resp);
  }
  @Override
  public void init() throws ServletException {
    super.init();
    productsService = new ProductsService();
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String uri = req.getServletPath();

    Response response = new Response();

    String page = req.getParameter("page");
    //String page = "1";

    System.out.println("PARAM PAGE: " + page);

    //store.setTableMetaPage(page);
    System.out.println(1);
    page = store.getTableMeta().getPage();
    System.out.println(2);
    int[] environment = store.getTableMeta().getEnvironment();

    System.out.println("SET PAGE: " + page);

    String limit = req.getParameter("limit");

    if (limit != null) {
      store.setTableMetaLimit(limit);
    } else {
      limit = store.getTableMeta().getLimit();
    }

    String offset = store.getTableMeta().getOffset();

    TableMeta tableMeta = new TableMeta(limit, offset);

    try {
      if ("/products".equals(uri)) {
        response = productsService.getList(tableMeta);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    store.setTableMetaTotal(response.getTotal());

    StoreData storeData = new StoreData(
      new TableMeta(limit, offset, response.getTotal(), page, environment),
      response.getProducts()
    );

    req.setAttribute("data", storeData);
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/store/pages/store.html.jsp");
    view.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}
}