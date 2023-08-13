package com.responses.products;

import com.entities.store.data.Products;

public class Response {
  private Products[] products;
  private String total;

  public Response() {
    products = new Products[0];
    total = "0";
  }

  public Response(Products[] products, String total) {
    this.products = products;
    this.total = total;
  }

  public Products[] getProducts() {
    return products;
  }
  public void setProducts(Products[] products) {
    this.products = products;
  }
  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

}
