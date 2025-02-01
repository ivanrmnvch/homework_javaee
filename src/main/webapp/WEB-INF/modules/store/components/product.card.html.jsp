<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="product-card">
  <div class="product-card__first-col">
  <button
    class="product__card-wrap"
    onclick="window.open('/homework_javaee-1.0-SNAPSHOT/product?id=' + ${param.id}, '_self')"
  >
    <jsp:include page="../../ui/image.html.jsp">
      <jsp:param name="imagePath" value="${param.imagePath}"/>
      <jsp:param name="iconHeight" value="128"/>
      <jsp:param name="iconWidth" value="160"/>
      <jsp:param name="imageBtn" value="true"/>
    </jsp:include>
  </button>
  </div>
  <div class="ml-2 product-card__info">
    <div class="mb-4">
      <c:out value="${param.name}"></c:out>
    </div>
    <div class="product-card__description mb-4">
      <c:out value="${param.description}"></c:out>
    </div>
    <div class="product-card__price">
      Стоимость:
      <c:out value="${param.price}"></c:out>
      &#8381;
    </div>
  </div>
</div>
<style>
  .product-card {
    display: flex;
    flex-direction: row;
    background-color: white;
    box-shadow: 2px 2px 5px -1px gray;
    width: 500px;
    border-radius: 8px;
    padding: 10px;
    margin: 10px;
  }
  .product-card__first-col {
    flex-basis: 160px;
  }
  .product-card__description {
      line-height: 20px;
      height: 60px;
      overflow: hidden;
  }
  .product-card__price {
    text-align: end;
  }
  .product-card__info {
    display: flex;
    flex-direction: column;
  }
  .product__card-wrap {
    border: none !important;
    background-color: white !important;
  }
</style>