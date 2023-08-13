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
  //private final int ENV_LENGTH = 3;

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
  public void setTableMetaPage(String page) {
    System.out.println("11");
    if (isTransition(page)) {
      System.out.println((123));
      return;
    }
    System.out.println("22");
    if (!pageNumberIsCorrect(page)) {
      return;
    }
    System.out.println("33");
    if (tableMeta.getNumberOfPage() <= 5) {
      System.out.println("page < 5");
      tableMeta.setPage(page);
      return;
    }
    // 1, 2, 3, ..., 6
    // 1, ..., 4, 5, 6
    System.out.println("44");
    if (tableMeta.outsideTheEnvironment(page)) {
      int pageNumber = Integer.parseInt(page);
      System.out.println(pageNumber + ">" + tableMeta.getLastEnv());
      if (pageNumber > tableMeta.getLastEnv()) {
        setPaginationState(page, "back");
        return;
      }
      System.out.println(pageNumber + "<" + tableMeta.getFirstEnv());
      if (pageNumber < tableMeta.getFirstEnv()) {
        setPaginationState(page, "forward");
      }
    } else {
      System.out.println("SET");
      tableMeta.setPage(page);
    }

//    if (checkFirstThreePages()) {
//      tableMeta.setPage(page);
//      return;
//    }
//    if (checkOnTheLastThreePages()) {
//      int numberOfPage = tableMeta.getNumberOfPage();
//      tableMeta.setPage(Integer.toString(numberOfPage - 2));
//    }
    // 1, ..., 5, 6, 7, ..., 10
    // 1, ..., 4, 5, 6, ..., 10
  }

  // 1, 2, 3, ..., 6
  // 1, ..., 4, 5, 6

  private int[] createNewEnvironment(String newPage, String transition) {
    int ENV_LENGTH = 3;
    int page = Integer.parseInt(newPage);
    int[] newEnvironment = new int[ENV_LENGTH];
    if (Objects.equals(transition, "forward")) {
      for (int env = 0; env < ENV_LENGTH; env++) {
        newEnvironment[env] = page + env;
      }
    }
    if (Objects.equals(transition, "back")) {
      for (int env = ENV_LENGTH; env > 0; env--) {
        newEnvironment[ENV_LENGTH - env] = page - env + 1;
      }
    }
    for (int i: newEnvironment) {
      System.out.print("new env: " + i + " ");
    }
    return newEnvironment;
  }

  private boolean pageNumberIsCorrect(String page) {
    boolean isNumeric = Common.isNumeric(page);
    if (!isNumeric) {
      return false;
    }
    int numberOfPage = tableMeta.getNumberOfPage();
    int pageNumber = Integer.parseInt(page);
    return pageNumber <= numberOfPage;
  }

  private boolean checkFirstThreePages() {
    int lastVisitedPage = getTableMeta().getLastVisitedPage();
    if (lastVisitedPage <= 3) {
      return true;
    }
    return false;
  }

  private boolean checkOnTheLastThreePages() {
    int lastVisitedPage = getTableMeta().getLastVisitedPage();
    int numberOfPage = tableMeta.getNumberOfPage();
    if (lastVisitedPage >= numberOfPage - 2) {
      return true;
    }
    return false;
  }


  // 1, 2, 3, ..., 6
  // 1, ..., 4, 5, 6

  // 1, ..., 5, 6, 7, ..., 10
  // 1, ..., 4, 5, 6, ..., 10
  private boolean isTransition(String transition) {
    System.out.println("transition" + transition);
    int ENV_LENGTH = 3;
    String nextPage;
    switch (transition) {
      case "forward":
        System.out.println(111);
        nextPage = Integer.toString(tableMeta.getFirstEnv() + ENV_LENGTH);
        System.out.println(222);
        setPaginationState(nextPage, transition);
        System.out.println(333);
        return true;
      case "back":
        System.out.println(444);
        nextPage = Integer.toString(tableMeta.getLastEnv() - ENV_LENGTH);
        System.out.println(555);
        setPaginationState(nextPage, transition);
        System.out.println(666);
        return true;
      default:
        System.out.println(777);
        return false;
    }
  }

  private void setPaginationState(String page, String transition) {
    tableMeta.setPage(page);
    int[] newEnvironment = createNewEnvironment(page, transition);
    tableMeta.setEnvironment(newEnvironment);
  }
}
