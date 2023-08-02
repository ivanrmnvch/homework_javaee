package com.services;

import com.utils.DatabaseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

import com.entities.Product;
import org.apache.commons.dbutils.DbUtils;

public class ProductsService {
  public Product[] getList(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
    DatabaseUtils databaseUtils = com.utils.DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    );
    ResultSet rs = null;
    Product[] products = null;

    try {
      rs = service.executeQuery("" +
        "SELECT " +
          "COUNT (*) OVER () as count," +
          "name," +
          "description," +
          "price," +
          "\"imagePath\"," +
          "brand," +
          "category " +
        "FROM products " +
        "LIMIT 10 " +
        "OFFSET 0;"
      );

      rs.next();
      int count = Integer.parseInt(rs.getString("count"));
      rs.previous();

      products = new Product[count];

      while (rs.next()) {
        int index = rs.getRow() - 1;
        products[index] = new Product(
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
    } finally {
      DbUtils.closeQuietly(connection, service, rs);
    }
    return products;
  }
}
