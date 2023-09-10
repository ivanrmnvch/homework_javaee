<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Cart</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
<jsp:include page="../../ui/header.html.jsp"></jsp:include>
  <div class="cart-page">
    <div class="cart-items">
      <c:forEach var="product" items="${cart.getProduct()}">
        <jsp:include page="../components/cart.card.html.jsp">
          <jsp:param name="id" value="${product.getId()}"/>
          <jsp:param name="name" value="${product.getName()}"/>
          <jsp:param name="price" value="${product.getPrice()}"/>
          <jsp:param name="imagePath" value="${product.getImagePath()}"/>
        </jsp:include>
      </c:forEach>
    </div>
  </div>
</body>
</html>
<style>
    .cart-page {
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 72px;
    }
    .cart-items {
      height: 100%;
    }

    .basket-card__image {
        object-fit: contain;
        width: 97px;
        height: 70px;
        border-radius: 3px;
        flex: 0 0 97px;
    }
    .basket-card__name {
        flex: 0 0 230px;
    }
    .basket-card__price {
        flex: 0 0 75px;
    }
    .basket-card__delete {
        flex: auto;
        object-fit: contain;
        width: 20px;
        height: 20px;
        cursor: pointer;
        border-radius: 3px;
    }
    .basket-card-btn {
        border: none;
        background-color: white;
        margin-left: 15px;
    }
</style>