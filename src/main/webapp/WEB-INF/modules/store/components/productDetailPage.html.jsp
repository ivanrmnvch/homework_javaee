<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Detail page</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
<jsp:include page="../../ui/header.html.jsp"></jsp:include>
<div class="product-detail-page">
  <div class="product-detail-page__content">
    <div class="col">
      <h3 class="mt-2 mb-0" style="margin-bottom: 5px;">
        ${Product.getName()}
      </h3>
      <jsp:include page="../../ui/image.html.jsp">
        <jsp:param name="imagePath" value="${Product.getImagePath()}"/>
        <jsp:param name="iconHeight" value="240"/>
        <jsp:param name="iconWidth" value="293"/>
      </jsp:include>
      <div class="col product-detail-page__footer" style="align-items: center;">
        <div class="row">
          <button
            ${user.isAdmin()}
            class="btn btn-green ${user.isAdmin()}"
            onclick="window.open('/homework_javaee-1.0-SNAPSHOT/basket/add?productId=' + ${Product.getId()}, '_self');"
          >
            Добавить в корзину
          </button>
        </div>
      </div>
    </div>
    <div class="product-detail-page__info col">
      <div class="product-detail-page__title">
        <h3 class="mt-2 ml-8">Описание</h3>
        <jsp:include page="../../ui/buttons/return-btn.html.jsp">
          <jsp:param name="action" value="products"/>
        </jsp:include>
      </div>
      <p class="product-detail-page__description mr-5 ml-8 mt-0">
        ${Product.getDescription()}
      </p>
      <div class="col ml-8 product-detail-page__footer">
        <div class="row">
          <h3 class="reset">Стоимость:</h3>
          <h2 class="ml-2 reset product-detail-page__price">
            ${Product.getPrice()}&#8381;
          </h2>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
<style>
    .product-detail-page {
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .product-detail-page__content {
        height: 400px;
        width: 700px;
        background-color: white;
        border-radius: 8px;
        box-shadow: 2px 2px 5px -1px gray;
        padding: 10px;
        display: flex;
    }

    .product-detail-page__description {
        padding: 10px;
        text-align: justify;
        overflow-y: auto;
        height: 210px;
        background-color: #f0f0f0;
        border-radius: 8px;
    }

    .product-detail-page__footer {
        height: 126px;
        justify-content: center;
    }
    .product-detail-page__price {
        line-height: 22px;
    }
    .product-detail-page__title {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .product-detail-page__info {
        width: 100%;
    }
    .disabled {
        background-color: #4caf507a !important;
    }
    .disabled:hover {
        background-color: #45a049a3 !important;
    }
</style>
