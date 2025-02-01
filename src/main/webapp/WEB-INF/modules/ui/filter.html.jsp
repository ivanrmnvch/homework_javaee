<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div
  id="filter"
  class="content__wrapper"
>
  <form
    name="filter"
    method="GET"
    action="products"
    autocomplete="off"
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
        class="mb-2"
      >
        Найти
      </button>
      <button
        onclick="window.open('/homework_javaee-1.0-SNAPSHOT/products?form=clear', '_self');"
        type="button">
        Сбросить
      </button>
    </div>
  </form>
</div>