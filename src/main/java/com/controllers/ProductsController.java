package com.controllers;

import com.entities.common.Filter;
import com.entities.common.Price;
import com.entities.store.StoreData;
import com.responses.products.Response;
import com.services.AuthService;
import com.services.ProductsService;
import com.entities.common.TableMeta;
import com.state.store.Store;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
  private AuthService authService;
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
    try {
      String categories = productsService.getFilterProperties("category");
      String brands = productsService.getFilterProperties("brand");
      store.getFilter().setCategories(categories);
      store.getFilter().setBrands(brands);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String uri = req.getServletPath();

    Response response = new Response();
    String userName = "Hello!";

    // получение фильтра со страницы
    String name = req.getParameter("name");
    String priceMin = req.getParameter("priceMin");
    String priceMax = req.getParameter("priceMax");
    String brand = req.getParameter("brand");
    String category = req.getParameter("category");

    // сохранение фильтра в state
    store.setFilterName(name);
    store.setFilterPrice(priceMin, priceMax);
    store.getFilter().setBrand(brand);
    store.getFilter().setCategory(category);

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
    String brands = store.getFilter().getBrands();
    String categories = store.getFilter().getCategories();

    TableMeta tableMeta = new TableMeta(limit, offset);

    Filter filter = new Filter(name, price, brand, category, brands, categories);

    try {
      if ("/products".equals(uri)) {
        response = productsService.getList(tableMeta, filter);
        userName = authService.getUserName(req);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    store.setTableMetaTotal(response.getTotal());

    StoreData storeData = new StoreData(
      new TableMeta(limit, offset, response.getTotal(), page, environment),
      response.getProduct(),
      filter
    );

    req.setAttribute("data", storeData);
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/store/pages/store.html.jsp");
    view.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}
}