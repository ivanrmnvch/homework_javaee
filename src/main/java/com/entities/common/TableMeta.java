package com.entities.common;

import java.io.Serializable;

public class TableMeta implements Serializable {
  private String limit;
  private String offset;
  private String total;
  private String page;
  private int[] environment;
  public TableMeta() {
    limit = "10";
    offset = "0";
    total = "0";
    page = "1";
    environment = new int[]{ 1 };
  }
  public TableMeta(String limit, String offset) {
    this.limit = limit;
    this.offset = offset;
    this.total = "0";
    this.page = "1";
    environment = new int[]{ 1 };
  }
  public TableMeta(String limit, String offset, String total, String page, int[] environment) {
    this.limit = limit;
    this.offset = offset;
    this.total = total;
    this.page = page;
    this.environment = environment;
  }
  public String getLimit() {
    return limit;
  }

  public void setLimit(String limit) {
    this.limit = limit;
  }

  public String getOffset() {
    return offset;
  }

  public void setOffset(String offset) {
    this.offset = offset;
  }

  public String getTotal() { return total; }

  public void setTotal(String total) {
    this.total = total;
  }
  public String getPage() {
    return page;
  }
  public void setPage(String page) {
    this.page = page;
  }

  public String getPagination() {
    float total = Float.parseFloat(this.total);
    float limit = Float.parseFloat(this.limit);

    int numberOfPage = (int)Math.ceil(total / limit);

    if (numberOfPage <= 5) {
      return getDefaultPagination(numberOfPage);
    }
    return getUserPagination(numberOfPage);
  }
  public int[] getEnvironment() {
    return environment;
  }
  public void setEnvironment(int[] environment) {
    this.environment = environment;
  }

  private String getDefaultPagination(int numberOfPage) {
    String pagination = "";
    for (int i = 0; i < numberOfPage; i++) {
      pagination += Integer.toString(i + 1);
      if (i < numberOfPage - 1) {
        pagination += ",";
      }
    }
    return pagination;
  }
  private String getUserPagination(int numberOfPage) {
    int page = Integer.parseInt(this.page);

    if (page <= 3) {
      return "1,2,3,forward," + numberOfPage;
    }
    if (page >= numberOfPage - 2) {
      String lastPages = "";
      int[] distances = { 2, 1 };
      for (int distance : distances) {
        lastPages += numberOfPage - distance + ",";
      }
      return "1,back," + lastPages + numberOfPage;
    }
    String pagination = "1,back,";
    for (int pageNum : environment) {
      pagination += pageNum + ",";
    }
    return pagination += "forward," + numberOfPage;
  }
}