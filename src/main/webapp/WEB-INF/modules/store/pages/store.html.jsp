<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Store</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">

  <link rel="stylesheet" href="assets/css/content/product.css" type="text/css">
</head>
<body class="app test">
  <div class="app-center">
    <jsp:include page="../../ui/header.html.jsp"></jsp:include>
    <div class="store-app row app-width">
      <div class="store-filter">
        <jsp:include page="../../ui/filter.html.jsp">
          <jsp:param name="name" value="${data.getFilter().getName()}"></jsp:param>
          <jsp:param name="priceMin" value="${data.getFilter().getPrice().getMin()}"></jsp:param>
          <jsp:param name="priceMax" value="${data.getFilter().getPrice().getMax()}"></jsp:param>
          <jsp:param name="brand" value="${data.getFilter().getBrand()}"></jsp:param>
          <jsp:param name="category" value="${data.getFilter().getCategory()}"></jsp:param>
          <jsp:param name="brands" value="${data.getFilter().getBrands()}"></jsp:param>
          <jsp:param name="categories" value="${data.getFilter().getCategories()}"></jsp:param>
        </jsp:include>
      </div>
      <div class="store-product col">
        <c:choose>
          <c:when test="${total == '0'}">
            <div class="empty-wrapper">
              <div class="empty-content">
                По вашему запросу ничего не найдено! Укажите корректные данные для поиска.
              </div>
            </div>
          </c:when>
          <c:otherwise>
            <c:forEach var="product" items="${data.getProduct()}">
              <jsp:include page="../components/product.card.html.jsp">
                <jsp:param name="id" value="${product.getId()}" />
                <jsp:param name="name" value="${product.getName()}"/>
                <jsp:param name="imagePath" value="${product.getImagePath()}"/>
                <jsp:param name="description" value="${product.getDescription()}"/>
                <jsp:param name="price" value="${product.getPrice()}"/>
              </jsp:include>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
    <div class="store-pagination row">
      <jsp:include page="../../ui/pagination.html.jsp">
        <jsp:param name="pagination" value="${data.getTableMeta().getPagination()}"/>
        <jsp:param name="page" value="${data.getTableMeta().getPage()}"/>
      </jsp:include>
      <jsp:include page="../../ui/dropbox.html.jsp">
        <jsp:param name="limit" value="${data.getTableMeta().getLimit()}"/>
      </jsp:include>
    </div>
  </div>
</body>
</html>
<style>
  .empty-wrapper {
      width: 540px;
      height: 232px;
      display: flex;
      justify-content: center;
      align-items: center;
  }
  .empty-content {
      text-align: center;
      width: 450px;
      height: 100px;
      background-color: white;
      border-radius: 8px;
      display: flex;
      justify-content: center;
      align-items: center;
      box-shadow: 2px 2px 5px -1px gray;
  }
</style>