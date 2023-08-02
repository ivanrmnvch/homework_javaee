<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div class="product-card">
  <img
    class="product-image"
    src="<c:out value="${param.imagePath}"></c:out>"
    alt="Название товара"
  >
  <div class="product-title">
    <c:out value="${param.name}"></c:out>
  </div>
  <div class="product-description">
    <p>
      <c:out value="${param.description}"></c:out>"
    </p>
  </div>
  <div class="product-price">
    <c:out value="${param.price}"></c:out> рублей
  </div>
</div>
</body>
</html>

<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
    }

    .product-card {
        width: 350px;
        border: 1px solid #ddd;
        border-radius: 8px;
        padding: 10px;
        margin: 20px;
    }

    .product-image {
        max-width: 100%;
        height: auto;
        border-radius: 8px;
    }

    .product-title {
        font-size: 18px;
        font-weight: bold;
        margin-top: 10px;
    }

    .product-description {
        height: 100px;
        overflow-y: scroll;
        margin-top: 5px;
    }

    .product-price {
        font-size: 16px;
        font-weight: bold;
        color: #007bff;
        margin-top: 10px;
    }
</style>
