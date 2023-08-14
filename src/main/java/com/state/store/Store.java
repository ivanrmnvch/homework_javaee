package com.state.store;
import com.utils.Common;

import com.state.common.TableMeta;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;

import java.io.Serializable;
import java.util.Objects;

@Stateful
public class Store implements Serializable {
  @EJB
  private TableMeta tableMeta;
  private String DEFAULT_PAGE_NUMBER = "1";
  private int ENV_LENGTH = 3;
  public Store() {}
  public TableMeta getTableMeta() {
    return tableMeta;
  }
  public void setTableMeta(TableMeta tableMeta) {
    this.tableMeta = tableMeta;
  }
  public void setTableMetaLimit(String limit) {
    tableMeta.setLimit(limit);
  }
  public void setTableMetaOffset(String offset) {
    tableMeta.setOffset(offset);
  }
  public void setTableMetaTotal(String total) {
    tableMeta.setTotal(total);
  }
  public void setTableMetaPage(String page, String action) {

    if (action != null) {
      transition(action);
      return;
    }

    boolean isNumeric = Common.isNumeric(page);

    if (!isNumeric) {
      tableMeta.setPage(DEFAULT_PAGE_NUMBER);
      return;
    }

    int numberOfPage = tableMeta.getNumberOfPage();
    int pageNumber = Integer.parseInt(page);

    if (pageNumber > numberOfPage) {
      tableMeta.setPage(DEFAULT_PAGE_NUMBER);
      return;
    }

    if (numberOfPage <= 5) {
      tableMeta.setPage(page);
      return;
    }

    if (tableMeta.outsideTheEnvironment(page)) {
      if (pageNumber > tableMeta.getLastEnv()) {
        setPaginationState(page, "back");
        return;
      }
      if (pageNumber < tableMeta.getFirstEnv()) {
        setPaginationState(page, "forward");
      }
    } else {
      tableMeta.setPage(page);
    }
  }

  private int[] createNewEnvironment(String newPage, String transition) {
    int page = Integer.parseInt(newPage);
    int[] newEnvironment = new int[ENV_LENGTH];
    if (Objects.equals(transition, "forward")) {
      int numberOfPage = tableMeta.getNumberOfPage();
      if (page + 2 > numberOfPage) {
        page -= 1;
      }
      for (int env = 0; env < ENV_LENGTH; env++) {
        newEnvironment[env] = page + env;
      }
    }
    if (Objects.equals(transition, "back")) {
      if (page - ENV_LENGTH + 1 <= 0) {
        page += 1;
      }
      for (int env = ENV_LENGTH; env > 0; env--) {
        newEnvironment[ENV_LENGTH - env] = page - env + 1;
      }
    }
    return newEnvironment;
  }

  private void transition(String transition) {
    String nextPage;
    if (Objects.equals(transition, "forward")) {
      nextPage = Integer.toString(tableMeta.getFirstEnv() + ENV_LENGTH);
      setPaginationState(nextPage, transition);
    } else if (Objects.equals(transition, "back")) {
      nextPage = Integer.toString(tableMeta.getLastEnv() - ENV_LENGTH);
      setPaginationState(nextPage, transition);
    } else if (Objects.equals(transition, "right")) {
      int newPage = tableMeta.getLastVisitedPage() + 1;
      if (newPage <= tableMeta.getNumberOfPage()) {
        boolean outside = tableMeta.outsideTheEnvironment(Integer.toString(newPage));
        if (outside) {
          setPaginationState(Integer.toString(newPage), "forward");
        } else {
          tableMeta.setPage(Integer.toString(newPage));
        }
      }
    } else if (Objects.equals(transition, "left")) {
      int newPage = tableMeta.getLastVisitedPage() - 1;
      if (newPage > 0) {
        boolean outside = tableMeta.outsideTheEnvironment(Integer.toString(newPage));
        if (outside) {
          setPaginationState(Integer.toString(newPage), "back");
        } else {
          tableMeta.setPage(Integer.toString(newPage));
        }
      }
    } else {
      tableMeta.setPage(DEFAULT_PAGE_NUMBER);
    }
  }

  private void setPaginationState(String page, String transition) {
    tableMeta.setPage(page);
    int[] newEnvironment = createNewEnvironment(page, transition);
    tableMeta.setEnvironment(newEnvironment);
  }
}
