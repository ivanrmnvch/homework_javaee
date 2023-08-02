<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Store</title>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<jsp:include page="../ui/header.html.jsp"></jsp:include>
<div class="container">
  <div class="container-row">
    <c:forEach var="product" items="${products}">
      <jsp:include page="product.card.html.jsp">
        <jsp:param name="name" value="${product.getName()}"/>
        <jsp:param name="imagePath" value="${product.getImagePath()}"/>
        <jsp:param name="description" value="${product.getDescription()}"/>
        <jsp:param name="price" value="${product.getPrice()}"/>
      </jsp:include>
    </c:forEach>
  </div>
</div>

<footer>
  <p>Контактная информация</p>
</footer>
</body>
</html>

<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    header {
        background-color: #333;
        color: #fff;
        padding: 10px;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
    }

    footer {
        background-color: #333;
        color: #fff;
        padding: 10px;
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100%;
    }

    .container {
        width: 1236px;
        margin: 100px 50px 70px;
    }

    .container-row {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        margin-bottom: 20px;
        border: 1px solid #ddd;
        padding: 10px;
    }
</style>
