package com.services;

import com.entities.store.data.Product;
import com.entities.store.data.Cart;
import com.utils.DatabaseUtils;
import jakarta.servlet.http.HttpServlet;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasketService extends HttpServlet {
    public Cart getCart(String userId) throws SQLException {
        DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
        Connection connection = databaseUtils.getConnection();
        Statement service = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );
        ResultSet rs = null;

        Product[] product;
        int numberOfLines = 0;

        try {
            rs = service.executeQuery("" +
                "SELECT " +
                    "p.id, " +
                    "p.name, " +
                    "p.price, " +
                    "p.\"imagePath\" " +
                "FROM basket b " +
                "LEFT JOIN products p ON b.\"productId\" = p.id " +
                "WHERE b.\"userId\" = '" + userId + "'" +
                "AND b.deleted IS FALSE"
            );

            rs.afterLast();
            rs.previous();
            numberOfLines = rs.getRow();
            rs.beforeFirst();

            product = new Product[numberOfLines];

            while (rs.next()) {
                int index = rs.getRow() - 1;
                product[index] = new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("price"),
                    rs.getString("imagePath")
                );
            }
        } catch (Exception e) {
            System.err.println("ERROR RECEIVING CART::" + e.getMessage().replace("ERROR: ", ""));
            return new Cart();
        } finally {
            DbUtils.closeQuietly(connection, service, rs);
        }
        return new Cart(
          product,
          Integer.toString(numberOfLines)
        );
    }

    public void addProduct(String userId, String productId) throws SQLException {
        DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
        Connection connection = databaseUtils.getConnection();
        Statement service = connection.createStatement();

        try {
            service.execute(
                "DO " +
                "$do$ " +
                    "BEGIN " +
                        "IF NOT EXISTS (" +
                                "SELECT 1 FROM basket " +
                                "WHERE \"productId\" = '" + productId + "' " +
                                "AND deleted IS FALSE" + ") " +
                            "THEN " +
                                "INSERT INTO basket (\"userId\", \"productId\", created, updated) " +
                                "VALUES ('"+ userId +"', '" + productId + "', now()::timestamp, now()::timestamp); " +
                        "END IF; " +
                    "END " +
                "$do$");
        } catch (Exception e) {
            System.err.println("ERROR ADDING A PRODUCT TO CART::" + e.getMessage().replace("ERROR: ", ""));
        } finally {
            DbUtils.closeQuietly(connection);
            DbUtils.closeQuietly(service);
        }
    }

    public void deleteProduct(String userId, String productId) throws SQLException {
        DatabaseUtils databaseUtils = DatabaseUtils.getInstance();
        Connection connection = databaseUtils.getConnection();
        Statement service = connection.createStatement();

        try {
            service.execute("" +
                "UPDATE basket " +
                "SET deleted = true " +
                "WHERE \"userId\" = '" + userId + "'" +
                "AND \"productId\" = '" + productId + "';"
            );
        } catch (Exception e) {
            System.err.println("ERROR DELETING A PRODUCT::" + e.getMessage().replace("ERROR: ", ""));
        } finally {
            DbUtils.closeQuietly(connection);
            DbUtils.closeQuietly(service);
        }
    }
}
