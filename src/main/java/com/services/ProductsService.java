package com.services;

import com.entities.common.Filter;
import com.entities.common.TableMeta;
import com.responses.products.Response;
import com.utils.DatabaseUtils;

import java.sql.*;
import java.util.Objects;

import com.entities.store.data.Products;
import org.apache.commons.dbutils.DbUtils;

public class ProductsService {
  public Response getList(TableMeta tableMeta, Filter filter) throws SQLException {
    DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
    Connection connection = databaseUtils.getConnection();
    Statement service = connection.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY
    );
    ResultSet rs = null;

    Products[] products;
    String total;

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
      createWhereSql(filter) +
      "ORDER BY id ASC " +
      "LIMIT " + tableMeta.getLimit() + " " +
      "OFFSET " + tableMeta.getOffset() + ";";


    System.out.println("SQL: " + query);

    try {
      rs = service.executeQuery(query);

      rs.afterLast();
      rs.previous();
      total = rs.getString("count");
      int numberOfLines = rs.getRow();
      rs.beforeFirst();

      products = new Products[numberOfLines];

      while (rs.next()) {
        int index = rs.getRow() - 1;
        products[index] = new Products(
          rs.getString("id"),
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
      products,
      total
    );
  }

  private String createWhereSql(Filter filter) {
    String where = "WHERE active ";
    String name = filter.getName();
    where += "AND (LOWER(name) LIKE LOWER('%" + name + "%')) ";
    where += "AND price BETWEEN '" + filter.getPrice().getMin() + "' AND '" + filter.getPrice().getMax() + "' ";
    String brand = filter.getBrand();
    if (!Objects.equals(brand, "")) {
      where += "AND brand = " + brand + " ";
    }
    String category = filter.getCategory();
    if (!Objects.equals(category, "")) {
      where += "AND category = " + category + " ";
    }
    return where;
  }
}
