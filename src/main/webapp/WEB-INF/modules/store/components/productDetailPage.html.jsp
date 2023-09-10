<%@ page contentType="text/html;charset=UTF-8" language="java" %>\
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
      <img class="product-card__image" src="${Product.getImagePath()}">
      <div class="col product-detail-page__footer" style="align-items: center;">
        <div class="row">
          <button
            class="btn btn-sign-in"
            onclick="addProduct(${Product.getId()})"
          >
            Добавить в корзину
          </button>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="product-detail-page__title">
        <h3 class="mt-2 ml-8">Описание</h3>
        <form
          class="form-return"
          action="products"
          method="GET"
        >
          <input class="btn-return" type="submit" value="&#10060;">
        </form>
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
<script>
  const addProduct = (productId) => {
     window.open('/homework_javaee-1.0-SNAPSHOT/basket/add?productId=' + productId, "_self");
  };
</script>
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

    .product-card__image {
        object-fit: contain;
        width: 293px;
        height: 240px;
        cursor: pointer;
        border-radius: 3px;
    }

    .product-detail-page__price {
        line-height: 22px;
    }
    .product-detail-page__title {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .btn-return {
        padding: 0;
        margin: 3px;
        width: 17px;
        height: 17px;
        background-color: white;
        border: none;
        text-align: center;
        cursor: pointer;
    }
    .form-return {
        text-align: end;
        margin-top: 7px;
    }
</style>
