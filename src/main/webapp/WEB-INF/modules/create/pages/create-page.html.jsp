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
      <form
        method="GET"
        action="product/create"
        autocomplete="off"
      >
        <div class="row create-edit-form">
          <c:forEach var="data" items="${page.getData()}">
            <div class="create-form row">
              <p class="create-form__name reset">
                  ${data[0]}
              </p>
              <input
                required
                class="create-text-field ml-4"
                name="${data[1]}"
                type="text">
            </div>
          </c:forEach>
        </div>
        <button
          type="submit"
        >
          Создать
        </button>
      </form>
    </div>
  </div>
</body>
</html>
<style>
    .create-page {
        background-color: white;
        border-radius: 8px;
        width: 680px;
        height: 300px;
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
    .create-form {
        width: 329px;
        margin: 5px 0 5px 0;
    }
    .create-form__name {
        max-width: 93px;
        flex: 1 1 93px;
    }
    .create-edit-form {
        flex-wrap: wrap;
        justify-content: space-between;
    }
</style>
