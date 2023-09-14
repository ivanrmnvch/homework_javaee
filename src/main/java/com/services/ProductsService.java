package com.services;

import com.entities.common.Filter;
import com.entities.common.TableMeta;
import com.responses.products.Response;
import com.utils.DatabaseUtils;

import java.sql.*;
import java.util.Objects;

import com.entities.store.data.Product;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.DbUtils;

import static com.utils.Common.isNumeric;

public class ProductsService extends HttpServlet {
  public Response getList(TableMeta tableMeta, Filter filter) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    );
    ResultSet rs = null;

    Product[] product;
    String total;

    String where = createWhereSql(filter);

    String query = "" +
      "SELECT " +
        "COUNT (*) OVER () as count," +
        "id," +
        "name," +
        "description," +
        "price," +
        "\"imagePath\"," +
        "brand," +
        "category " +
      "FROM products " +
       where +
      "ORDER BY id ASC " +
      "LIMIT " + tableMeta.getLimit() + " " +
      "OFFSET " + tableMeta.getOffset() + ";";

    try {
      rs = service.executeQuery(query);

      rs.afterLast();
      rs.previous();
      total = rs.getString("count");
      int numberOfLines = rs.getRow();
      rs.beforeFirst();

      product = new Product[numberOfLines];

      while (rs.next()) {
        int index = rs.getRow() - 1;
        product[index] = new Product(
          rs.getString("id"),
          rs.getString("name"),
          rs.getString("description"),
          rs.getString("price"),
          rs.getString("imagePath"),
          rs.getString("brand"),
          rs.getString("category")
        );
      }
    } catch (Exception e) {
      System.err.println("ERROR GETTING THE LIST OF PRODUCTS::" + e.getMessage().replace("ERROR: ", ""));
      return new Response();
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
    return new Response(
      product,
      total
    );
  }

  public Product getProduct(String id) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
    );
    ResultSet rs = null;

    Product product = new Product();

    try {
      rs = service.executeQuery("" +
              "SELECT " +
                "id," +
                "name," +
                "description," +
                "price," +
                "\"imagePath\"," +
                "brand " +
              "FROM products " +
              "WHERE id =" + id + ";"
      );

      while (rs.next()) {
        product = new Product(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("price"),
                rs.getString("imagePath"),
                rs.getString("brand")
        );
      }
    } catch (Exception e) {
      System.err.println("ERROR RECEIVING PRODUCT::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
    return product;
  }

  public String getFilterProperties(String property) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
    );
    ResultSet rs = null;

    String properties = "";

    try {
      rs = service.executeQuery("" +
        "SELECT DISTINCT " +
          property + " " +
        "FROM products"
      );

      rs.afterLast();
      rs.previous();
      int numberOfLines = rs.getRow();
      rs.beforeFirst();

      while (rs.next()) {
        int index = rs.getRow();
        if (index == numberOfLines) {
          properties += rs.getString(property);
          break;
        }
        properties += rs.getString(property) + ",";
      }
    } catch(Exception e) {
      System.err.println("ERROR GETTING THE LIST OF PROPERTIES::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
    return properties;
  }

  public void addProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement();

    Product product = new Product(
      null,
      req.getParameter("name"),
      req.getParameter("description"),
      req.getParameter("price"),
      req.getParameter("imagePath"),
      req.getParameter("brand"),
      req.getParameter("category")
    );


    boolean productIsValid = product.productIsValid();

    if (!productIsValid) {
      return;
    }

    String sql = String.format("" +
      "INSERT INTO products (name, description, price, \"imagePath\", brand, category) " +
      "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
      product.getName(),
      product.getDescription(),
      product.getPrice(),
      product.getImagePath(),
      product.getBrand(),
      product.getCategory()
    );

    try {
      service.execute(sql);
    } catch (Exception e) {
      System.out.println("ERROR ADDING PRODUCT::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection);
      DbUtils.closeQuietly(service);
    }
  }


  public com.responses.products.search.Response productSearch(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    );
    ResultSet rs = null;

    Product product = new Product();

    String searchValue = req.getParameter("searchValue");

    if (!isNumeric(searchValue)) {
      return new com.responses.products.search.Response(
        product,
        false,
        "Ошибка ввода!"
      );
    }

    String sql = "" +
            "SELECT " +
            "id," +
            "name," +
            "description," +
            "price," +
            "\"imagePath\"," +
            "brand," +
            "category," +
            "active " +
            "FROM products " +
            "WHERE id = " + searchValue + " " +
            "LIMIT 1;";

    try {
      rs = service.executeQuery(sql);

      rs.afterLast();
      rs.previous();
      int numberOfLines = rs.getRow();

      if (numberOfLines == 0) {
        return new com.responses.products.search.Response(
          product,
          false,
          "Товара с таким id не существует! Попробуйте ещё раз:"
        );
      }

      rs.beforeFirst();


      while (rs.next()) {
        product = new Product(
          rs.getString("id"),
          rs.getString("name"),
          rs.getString("description"),
          rs.getString("price"),
          rs.getString("imagePath"),
          rs.getString("brand"),
          rs.getString("category"),
          rs.getBoolean("active")
        );
      }
    } catch(Exception e) {
      System.err.println("PRODUCT SEARCH ERROR::" + e.getMessage().replace("ERROR: ", ""));
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
    return new com.responses.products.search.Response(
      product,
      true,
      ""
    );
  }

  public boolean productUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement();

    String id = req.getParameter("id");

    boolean isNumber = isNumeric(id);

    if (!isNumber) {
      return false;
    }

    String name = req.getParameter("name");
    String description = req.getParameter("description");
    String price = req.getParameter("price");
    String imagePath = req.getParameter("imagePath");
    String brand = req.getParameter("brand");
    String category = req.getParameter("category");
    String active = req.getParameter("active");

    String sql = String.format("" +
      "UPDATE products " +
        "SET (name, description, price, \"imagePath\", brand, category, active) = ('%s', '%s', '%s', '%s', '%s', '%s', '%s') " +
      "WHERE id = '%s'",
      name, description, price, imagePath, brand, category, active, id
    );

    try {
      service.execute(sql);
      return true;
    } catch (Exception e) {
      System.out.println("PRODUCT UPDATE ERROR::" + e.getMessage().replace("ERROR: ", ""));
      return false;
    } finally {
      DbUtils.closeQuietly(connection);
      DbUtils.closeQuietly(service);
    }
  }

  private String createWhereSql(Filter filter) {
    String where = "WHERE active IS TRUE ";
    String name = filter.getName();
    where += "AND (LOWER(name) LIKE LOWER('%" + name + "%')) ";
    where += "AND price BETWEEN '" + filter.getPrice().getMin() + "' AND '" + filter.getPrice().getMax() + "' ";
    String brand = filter.getBrand();
    if (!Objects.equals(brand, "")) {
      where += "AND brand = '" + brand + "' ";
    }
    String category = filter.getCategory();
    if (!Objects.equals(category, "")) {
      where += "AND category = '" + category + "' ";
    }
    return where;
  }
}
