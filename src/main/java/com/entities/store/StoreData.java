package com.entities.store;

import com.entities.common.Filter;
import com.entities.store.data.Products;
import com.entities.common.TableMeta;

import java.io.Serializable;

public class StoreData implements Serializable {
  private TableMeta tableMeta;
  private Products[] products;
  private Filter filter;

  public StoreData() {
    tableMeta = new TableMeta();
    products = new Products[0];
  }

  public StoreData(TableMeta tableMeta, Products[] products, Filter filter) {
    this.tableMeta = tableMeta;
    this.products = products;
    this.filter = filter;
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
  public Filter getFilter() {
    return filter;
  }
  public void setFilter(Filter filter) {
    this.filter = filter;
  }
}
