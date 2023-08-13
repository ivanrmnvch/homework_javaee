package com.state.common;

import jakarta.ejb.Stateful;

import java.io.Serializable;
@Stateful
public class TableMeta implements Serializable {
  private String limit;
  private String offset;
  private String total;
  private String page;
  private int[] environment;
  //private String state;

  public TableMeta() {
    limit = "10";
    offset = "0";
    total = "0";
    page = "1";
    environment = new int[]{1, 2, 3};
    //state = null;
  }

  public String getLimit() {
    return limit;
  }
  public void setLimit(String limit) {
    this.limit = limit;
  }
  public String getOffset() {
    int limit = Integer.parseInt(this.limit);
    int page = Integer.parseInt(this.page);
    return Integer.toString((page - 1) * limit);
  }
  public void setOffset(String offset) {
    this.offset = offset;
  }
  public String getTotal() {
    return total;
  }
  public void setTotal(String total) {
    this.total = total;
  }
  public String getPage() {
    return page;
  }
  public void setPage(String page) {
    this.page = page;
  }
  public void setEnvironment(int[] environment) {
    this.environment = environment;
  }
  public int[] getEnvironment() {
    return environment;
  }
  public int getNumberOfPage() {
    return (int)Math.ceil(Float.parseFloat(total) / Float.parseFloat(limit));
  }

  public int getLastVisitedPage() {
    return Integer.parseInt(page);
  }

//  public int findOutThePosition() {
//    int pageNumber = Integer.parseInt(page);
//    if (pageNumber == getFirstEnv()) {
//      return 0;
//    }
//    if (pageNumber == getLastEnv()) {
//      return 2;
//    }
//    return 1;
//  }
  public boolean outsideTheEnvironment(String page) {
    int pageNumber = Integer.parseInt(page);
    for (int env: environment) {
      if (pageNumber == env) {
        return false;
      }
    }
    return true;
  }
  public int getLastEnv() {
    return environment[environment.length - 1];
  }

  public int getFirstEnv() {
    return environment[0];
  }
//  public String getState() {
//    return state;
//  }
//  public void setState(String state) {
//    this.state = state;
//  }
}
