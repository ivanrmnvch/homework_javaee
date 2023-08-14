package com.controllers;

import com.entities.common.Filter;
import com.entities.common.Price;
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

    // получение фильтра со страницы
    String name = req.getParameter("name");
    System.out.println("PARAM NAME: " + name);
    String priceMin = req.getParameter("priceMin");
    System.out.println("PARAM priceMin: " + priceMin);
    String priceMax = req.getParameter("priceMax");
    System.out.println("PARAM priceMax: " + priceMax);
    String brand = req.getParameter("brand");
    System.out.println("PARAM brand: " + brand);
    String category = req.getParameter("category");
    System.out.println("PARAM category: " + category);

    // сохранение фильтра в state
    store.setFilterName(name);
    store.setFilterPrice(priceMin, priceMax);
    store.setFilterBrand(brand);
    store.setFilterCategory(category);

    // получение данных о пагинации со страницы
    String action = req.getParameter("action");
    String page = req.getParameter("page");
    String limit = req.getParameter("limit");

    // сохранение данных о пагинации в state
    store.setTableMetaPage(page, action);
    store.setTableMetaLimit(limit);

    // получение данных о пагинации из state
    String offset = store.getTableMeta().getOffset();
    int[] environment = store.getTableMeta().getEnvironment();
    limit = store.getTableMeta().getLimit();
    page = store.getTableMeta().getPage();

    // получение данных о фильтре из state
    name = store.getFilter().getName();
    Price price = new Price(
      store.getFilter().getPrice().getMin(),
      store.getFilter().getPrice().getMax()
    );
    brand = store.getFilter().getBrand();
    category = store.getFilter().getCategory();

    TableMeta tableMeta = new TableMeta(limit, offset);
    Filter filter = new Filter(name, price, brand, category);

    try {
      if ("/products".equals(uri)) {
        response = productsService.getList(tableMeta, filter);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    store.setTableMetaTotal(response.getTotal());

    StoreData storeData = new StoreData(
      new TableMeta(limit, offset, response.getTotal(), page, environment),
      response.getProducts(),
      filter
    );

    System.out.println(store.getFilter().getBrand());
    System.out.println(store.getFilter().getPrice().getMax());
    System.out.println(store.getFilter().getName());

    req.setAttribute("data", storeData);
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/store/pages/store.html.jsp");
    view.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}
}