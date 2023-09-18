<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Product update</title>
    <link rel="stylesheet" href="assets/css/index.css" type="text/css">
  </head>
  <body class="app">
    <div class="center-app col">
      <jsp:include page="../header.html.jsp"></jsp:include>
      <div class="product-update-page">
        <jsp:include page="../buttons/return-btn.html.jsp">
          <jsp:param name="action" value="profile"/>
        </jsp:include>
        <div class="page-wrapper col">
          <div class="text ${style}" >
            ${message}
          </div>
          <form
            method="GET"
            action="create"
            class="row"
          >
            <div class="text-create">
              Создать новую позицию:
            </div>
            <button
              class="ml-2 product__card-wrap"
            >
              <jsp:include page="../image.html.jsp">
                <jsp:param name="imagePath" value="assets/icons/add-square-svgrepo-com.svg"/>
                <jsp:param name="iconHeight" value="25"/>
                <jsp:param name="iconWidth" value="25"/>
                <jsp:param name="imageBtn" value="true"/>
              </jsp:include>
            </button>
          </form>
          <form
            method="GET"
            action="edit"
            class="row product-update-edit-form"
          >
            <input
              required
              class="profile-text-field"
              type="number"
              name="searchValue"
              placeholder="Enter id"
            >
            <button
              class="ml-2 product__card-wrap"
            >
              <jsp:include page="../image.html.jsp">
                <jsp:param name="imagePath" value="assets/icons/edit-svgrepo-com.svg"/>
                <jsp:param name="iconHeight" value="25"/>
                <jsp:param name="iconWidth" value="25"/>
                <jsp:param name="imageBtn" value="true"/>
              </jsp:include>
            </button>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
<style>
  .product-update-page {
    height: 150px;
    width: 300px;
    background-color: white;
    border-radius: 8px;
    padding: 10px;
    box-shadow: 2px 2px 5px -1px gray;
  }
  .page-wrapper {
      height: calc(100% - 23px);
      justify-content: space-evenly;
      text-align: center;
  }
  .text {
      font-size: 18px;
  }
  .text-create {
      margin: 4px 0 0 31px;

  }
  .red {
      color: red;
  }
  .green {
      color: green;
  }
  .product__card-wrap {
      border: none !important;
      background-color: white !important;
  }
  .profile-text-field {
      font-size: 12px;
      padding: 10px;
      border-radius: 3px;
      border: 1px solid #ccc;
      height: 20px;
      width: 150px;
      margin-top: 4px;
  }
  .product-update-edit-form {
      justify-content: center;
  }
</style>
