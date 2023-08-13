package com.entities.store;

import com.entities.store.data.Products;
import com.entities.common.TableMeta;

import java.io.Serializable;

public class StoreData implements Serializable {
  private TableMeta tableMeta;
  private Products[] products;

  public StoreData() {
    tableMeta = new TableMeta();
    products = new Products[0];
  }

  public StoreData(TableMeta tableMeta, Products[] products) {
    this.tableMeta = tableMeta;
    this.products = products;
  }

  public TableMeta getTableMeta() {
    return tableMeta;
  }

  public void setTableMeta(TableMeta tableMeta) {
    this.tableMeta = tableMeta;
  }

  public Products[] getProducts() {
    return products;
  }

  public void setProducts(Products[] products) {
    this.products = products;
  }

}
