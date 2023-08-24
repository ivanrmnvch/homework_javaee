<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="content__wrapper">
  <form
    name="filter"
    method="GET"
    action="products"
  >
    <div class="content__filter">
      <div class="mb-1 content__filter-name">
        Название
      </div>
      <input
        placeholder="Введите название товара"
        class="mb-2 content__filter-inputs"
        type="text"
        name="name"
        value="${param.name}"
      >
      <div class="mb-1 content__filter-name">
        Категория товара
      </div>
      <select
        class="mb-2 content__filter-inputs"
        name="category"
      >
        <c:forEach var="category" items="${param.categories.split(',')}">
          <c:choose>
            <c:when test="${param.category == category}">
              <option value="${category}" selected>
                ${category}
              </option>
            </c:when>
            <c:otherwise>
              <option value="${category}">
                  ${category}
              </option>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </select>
      <div class="mb-1 content__filter-name">
        Бренд
      </div>
      <select
        class="mb-2 content__filter-inputs"
        name="brand"
      >
        <c:forEach var="brand" items="${param.brands.split(',')}">
          <c:choose>
            <c:when test="${param.brand == brand}">
              <option value="${brand}" selected>
                  ${brand}
              </option>
            </c:when>
            <c:otherwise>
              <option value="${brand}">
                  ${brand}
              </option>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </select>
      <div class="content__filter-price">
        <div class="mb-1">
          Цена
        </div>
        <div class="mb-2 content__filter-price-row">
          <span class="mr-1">
            от
          </span>
          <input
            class="content__filter-price__input"
            type="number"
            name="priceMin"
            placeholder="0"
            value="${param.priceMin}"
          >
          <span class="mr-1 ml-1">до</span>
          <input
            class="content__filter-price__input"
            placeholder="300000"
            type="number"
            name="priceMax"
            value="${param.priceMax}"
          >
        </div>
      </div>
      <button
        type="submit"
      >
        Найти
      </button>
    </div>
  </form>
</div>

<style>
  .content__wrapper {
    margin-top: 40px;
    width: 230px;
    height: 250px;
    position: fixed;
    left: 165px;
  }
  .content__filter {
    display: flex;
    flex-direction: column;
    margin: 10px;
    padding: 10px;
    background-color: white;
    box-shadow: 2px 2px 5px -1px gray;
    border-radius: 8px;
    height: calc(100% - 40px);
    width: calc(100% - 40px);
    font-family: Arial, sans-serif;
    font-size: 14px;
  }
  .content__filter-inputs {
    width: 100%;
    height: 19px;
  }
  .content__filter-price {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  .content__filter-price-row {
    display: flex;
    flex-direction: row;
    width: auto;
    align-items: baseline;
  }
  .content__filter-price__input {
    width: 74px;
    height: 19px;
  }
  .content__filter-name {
      text-align: start;
  }
</style>