package com.state.store;
import com.state.common.Filter;
import com.state.common.FilterProperties;
import com.state.common.Price;
import com.utils.Common;

import com.state.common.TableMeta;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;

import java.io.Serializable;
import java.util.Objects;

import static com.utils.Common.isNumeric;

@Stateful
public class Store implements Serializable {
  @EJB
  private TableMeta tableMeta;
  @EJB
  private Filter filter;
  @EJB
  private FilterProperties filterProperties;
  private String DEFAULT_PAGE_NUMBER = "1";
  private int ENV_LENGTH = 3;
  public Store() {}
  public TableMeta getTableMeta() {
    return tableMeta;
  }
  public void setTableMeta(TableMeta tableMeta) {
    this.tableMeta = tableMeta;
  }
  public Filter getFilter() {
    return filter;
  }
  public void setFilter(Filter filter) {
    this.filter = filter;
  }
  public FilterProperties getFilterProperties() {
    return filterProperties;
  }
  public void setFilterProperties(FilterProperties filterProperties) {
    this.filterProperties = filterProperties;
  }
  public void setTableMetaLimit(String limit) {
    if (limit != null) {
      tableMeta.setLimit(limit);
    }
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

    boolean isNumeric = isNumeric(page);

    if (!isNumeric) {
      //tableMeta.setPage(DEFAULT_PAGE_NUMBER);
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
      System.out.println(4);
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

  public void setFilterName(String name) {
    if (name != null) {
      filter.setName(name);
    }
  }
  public void setFilterPrice(String min, String max) {
    if (!isNumeric(min)) {
      return;
    }
    if (!isNumeric(max)) {
      return;
    }
    if (Integer.parseInt(min) > Integer.parseInt(max)) {
      return;
    }
    filter.getPrice().setMin(min);
    filter.getPrice().setMax(max);
  }
  public void setFilterBrand(String brand) {
    if (Objects.equals(brand, "") || brand == null) {
      return;
    }
    if (Objects.equals(brand, "all")) {
      filter.setBrand("");
      return;
    }
//    String[] brandList = new String[]{
//      "asus",
//      "honor"
//    };
    String[] brandList = filterProperties.getBrands().split(", ");
    for (String brandName : brandList) {
      if (brandName.equals(brand)) {
        filter.setBrand(brand);
        return;
      }
    }
  }
  public void setFilterCategory(String category) {
    if (Objects.equals(category, "") || category == null) {
      return;
    }
    if (Objects.equals(category, "all")) {
      filter.setCategory("");
      return;
    }
//    String[] categoryList = new String[]{
//      "laptop",
//      "tv"
//    };
    String[] categoryList = filterProperties.getCategories().split(", ");
    for (String categoryName : categoryList) {
      if (categoryName.equals(category)) {
        filter.setCategory(category);
        return;
      }
    }
  }
//  public void setFilterPropertiesCategories(String categories) {
//    filterProperties.setCategories(categories);
//  }
//  public void setFilterPropertiesBrands(String brands) {
//    filterProperties.setBrands(brands);
//  }
}
