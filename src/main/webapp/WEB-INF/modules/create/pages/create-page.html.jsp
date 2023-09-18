<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create Page</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
  <div class="center-app col">
    <jsp:include page="../../ui/header.html.jsp"></jsp:include>
    <div class="create-page">
      <div class="col">
        <jsp:include page="../../ui/buttons/return-btn.html.jsp">
          <jsp:param name="action" value="profile"/>
        </jsp:include>
        <form
          method="GET"
          action="product/create"
          autocomplete="off"
        >
          <div class="row create-edit-form">
            <c:forEach var="data" items="${page.getData()}">
              <div class="create-form row">
                <p class="create-form__name reset col">
                    ${data[0]}
                </p>
                <input
                  required
                  class="create-text-field ml-4"
                  name="${data[1]}"
                  type="${data[2]}">
              </div>
            </c:forEach>
          </div>
          <div class="btn-wrapper">
            <button
              class="btn-create"
              type="submit"
            >
              Создать
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
<style>
    .create-page {
        background-color: white;
        border-radius: 8px;
        width: 730px;
        height: 200px;
        box-shadow: 2px 2px 5px -1px gray;
        padding: 10px;
    }
    .create-text-field {
        font-size: 14px;
        padding: 10px;
        border-radius: 3px;
        border: 1px solid #ccc;
        height: 28px;
        flex: 1 1 207px;
    }
     /*todo вынести общие классы для edit/create page */
    .create-form {
        width: 329px;
        margin: 5px 0 5px 0;
    }
    .create-form__name {
        justify-content: center;
        max-width: 93px;
        flex: 1 1 93px;
    }
    .create-edit-form {
        padding: 0 30px;
        flex-wrap: wrap;
        justify-content: space-between;
    }
    .btn-create {
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
    .btn-create:hover {
        background-color: #45a049;
    }
    .btn-wrapper {
        display: flex;
        flex-direction: row;
        justify-content: start;
    }
</style>
