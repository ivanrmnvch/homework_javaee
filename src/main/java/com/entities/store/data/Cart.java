package com.entities.store.data;

import java.io.Serializable;

public class Cart implements Serializable {
  private Product[] product;
  private String count;

  public Cart() {
    product = new Product[0];
    count = "0";
  }

  public Cart(Product[] product, String count) {
    this.product = product;
    this.count = count;
  }

  public Product[] getProduct() {
    return product;
  }
  public void setProduct(Product[] product) {
    this.product = product;
  }
  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

}
