package com.entities.store;

import com.entities.common.Filter;
import com.entities.store.data.Product;
import com.entities.common.TableMeta;

import java.io.Serializable;

public class StoreData implements Serializable {
  private TableMeta tableMeta;
  private Product[] product;
  private Filter filter;

  public StoreData() {
    tableMeta = new TableMeta();
    product = new Product[0];
    filter = new Filter();
  }

  public StoreData(TableMeta tableMeta, Product[] product, Filter filter) {
    this.tableMeta = tableMeta;
    this.product = product;
    this.filter = filter;
  }

  public TableMeta getTableMeta() {
    return tableMeta;
  }

  public void setTableMeta(TableMeta tableMeta) {
    this.tableMeta = tableMeta;
  }

  public Product[] getProduct() {
    return product;
  }

  public void setProduct(Product[] product) {
    this.product = product;
  }
  public Filter getFilter() {
    return filter;
  }
  public void setFilter(Filter filter) {
    this.filter = filter;
  }
}
