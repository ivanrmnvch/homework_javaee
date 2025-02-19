package com.controllers;

import com.entities.common.Filter;
import com.entities.common.Price;
import com.entities.store.StoreData;
import com.entities.store.data.Cart;
import com.entities.store.data.User;
import com.responses.products.Response;
import com.services.AuthService;
import com.services.BasketService;
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
import java.util.Objects;

@WebServlet({
  "/products",
})
public class ProductsController extends HttpServlet {
  @EJB
  private Store store;
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
    User user = new User();
    Cart cart = new Cart();

    String name;
    String priceMin;
    String priceMax;
    String brand;
    String category;
    String form = req.getParameter("form");

    System.out.println("FORM "+ form);

    if (Objects.equals(form, "clear")) {
//      name = "";
//      priceMin = "";
//      priceMax = "";
//      brand = "";
//      category = "";
      store.clearFilter();
    } else {
      // получение фильтра со страницы
      name = req.getParameter("name");
      priceMin = req.getParameter("priceMin");
      priceMax = req.getParameter("priceMax");
      brand = req.getParameter("brand");
      category = req.getParameter("category");

      // сохранение фильтра в state
      store.setFilterName(name);
      store.setFilterPrice(priceMin, priceMax);
      store.getFilter().setBrand(brand);
      store.getFilter().setCategory(category);
    }

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
    System.out.println("BRAND " + brand);
    category = store.getFilter().getCategory();
    String brands = store.getFilter().getBrands();
    String categories = store.getFilter().getCategories();

    TableMeta tableMeta = new TableMeta(limit, offset);

    Filter filter = new Filter(name, price, brand, category, brands, categories);

    try {
      if ("/products".equals(uri)) {
        response = productsService.getList(tableMeta, filter);
        user = authService.getUserInfo(req);
        if (user.getUserIsAuthorized()) {
          cart = basketService.getCart(user.getUserId());
        }
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

    req.setAttribute("cart", cart);
    req.setAttribute("data", storeData);
    req.setAttribute("user", user);
    req.setAttribute("total", response.getTotal());
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/modules/store/pages/store.html.jsp");
    view.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {}
}