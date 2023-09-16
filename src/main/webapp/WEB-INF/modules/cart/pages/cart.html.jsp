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
    <div class="cart-page-items">
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