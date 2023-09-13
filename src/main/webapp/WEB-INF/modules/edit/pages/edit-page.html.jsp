<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Page</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
  <div class="center-app col">
    <jsp:include page="../../ui/header.html.jsp"></jsp:include>
    <div class="edit-page">
      <div class="col">
        <form
          class="form-return"
          action="profile"
          method="GET"
        >
          <input class="btn-return" type="submit" value="&#10060;">
        </form>
        <form
          method="GET"
          action="product/search"
        >
<%--          <div class="row edit-page-form">--%>
<%--            <c:forEach var="data" items="${edit.getData()}">--%>
<%--              <div class="edit-form row">--%>
<%--                <p class="edit-form__name reset">--%>
<%--                    ${data[0]}--%>
<%--                </p>--%>
<%--                <input--%>
<%--                  class="edit-text-field ml-4"--%>
<%--                  name="${data[1]}"--%>
<%--                  type="text"--%>
<%--                >--%>
<%--              </div>--%>
<%--            </c:forEach>--%>
<%--            <button--%>
<%--              type="submit"--%>
<%--              class="btn-search"--%>
<%--            >--%>
<%--              Поиск--%>
<%--            </button>--%>
<%--          </div>--%>
        </form>
        <form
          method="GET"
          action="product/update"
          autocomplete="off"
        >
          <div class="row edit-page-form">
            <c:forEach var="data" items="${product.getEditPageData()}">
              <c:choose>
                <c:when test="${data[1] == 'id'}">
                  <div class="edit-form row">
                    <p class="edit-form__name reset">
                        ${data[0]}
                    </p>
                    <input
                      readonly
                      required
                      class="edit-text-field ml-4"
                      name="${data[1]}"
                      type="text"
                      value="${data[2]}"
                    >
                  </div>
                </c:when>
                <c:otherwise>
                  <div class="edit-form row">
                    <p class="edit-form__name reset">
                        ${data[0]}
                    </p>
                    <input
                      maxlength="1500"
                      required
                      class="edit-text-field ml-4"
                      name="${data[1]}"
                      type="text"
                      value="${data[2]}"
                    >
                  </div>
                </c:otherwise>
              </c:choose>

            </c:forEach>
          </div>
          <div class="btn-wrapper">
            <button
              class="btn-edit"
              type="submit"
            >
              Редактировать
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
<style>
    .edit-page {
        background-color: white;
        border-radius: 8px;
        width: 730px;
        height: 400px;
        box-shadow: 2px 2px 5px -1px gray;
        padding: 10px;
    }
    .edit-text-field {
        font-size: 14px;
        padding: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
        height: 28px;
        flex: 1 1 207px;
    }
    .edit-form {
        width: 329px;
        margin: 5px 0 5px 0;
    }
    .edit-form__name {
        max-width: 93px;
        flex: 1 1 93px;
    }
    .edit-page-form {
        padding: 0 30px;
        flex-wrap: wrap;
        justify-content: space-between;
    }
    .form-return {
        text-align: end;
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
    .btn-edit {
        border: none;
        color: white;
        margin: 18px;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 3px;
        cursor: pointer;
        width: 30%;
        background-color: #4CAF50;
    }
    .btn-edit:hover {
        background-color: #45a049;
    }
    .btn-wrapper {
        display: flex;
        flex-direction: row;
        justify-content: start;
    }
    .btn-search {
        border: none;
        color: white;
        margin: 18px;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 3px;
        cursor: pointer;
        width: 30%;
        background-color: #4CAF50;
    }
    .btn-search:hover {
        background-color: #45a049;
    }
    .btn-wrapper {
        display: flex;
        flex-direction: row;
        justify-content: start;
    }
</style>
