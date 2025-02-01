package com.responses.products;

import com.entities.store.data.Product;

public class Response {
  private Product[] product;
  private String total;

  public Response() {
    product = new Product[0];
    total = "0";
  }

  public Response(Product[] product, String total) {
    this.product = product;
    this.total = total;
  }

  public Product[] getProduct() {
    return product;
  }
  public void setProduct(Product[] product) {
    this.product = product;
  }
  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

}
