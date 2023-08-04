<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="product-card">
  <div class="product-card__first-col">
    <img
      class="product-card__image"
      src="<c:out value="${param.imagePath}"></c:out>"
    >
  </div>
  <div class="product-card__info">
    <div>
      <c:out value="${param.name}"></c:out>
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
  .product-card__image {
    object-fit: contain;
    width: 160px;
    height: 128px;
    cursor: pointer;
    border-radius: 3px;
  }
  .product-card__image:hover {
    border: 1px solid white;
    width: 158px;
    height: 126px;
  }
  .product-card__image:active {
    border: 2px solid white;
    width: 156px;
    height: 124px;
  }
  product-card__info {
    display: flex;
    flex-direction: column;
  }
</style>

<%--<div class="product-card">--%>
<%--  <img--%>
<%--    class="product-image"--%>
<%--    src="<c:out value="${param.imagePath}"></c:out>"--%>
<%--    alt="Название товара"--%>
<%--  >--%>
<%--  <div class="product-title">--%>
<%--    <c:out value="${param.name}"></c:out>--%>
<%--  </div>--%>
<%--  <div class="product-description">--%>
<%--    <p>--%>
<%--      <c:out value="${param.description}"></c:out>"--%>
<%--    </p>--%>
<%--  </div>--%>
<%--  <div class="product-price">--%>
<%--    <c:out value="${param.price}"></c:out> рублей--%>
<%--  </div>--%>
<%--</div>--%>