<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Product update</title>
    <link rel="stylesheet" href="assets/css/index.css" type="text/css">
  </head>
  <body class="app">
    <div class="center-app col">
      <jsp:include page="../ui/header.html.jsp"></jsp:include>
      <div class="product-update-page">
        <jsp:include page="buttons/return-btn.html.jsp">
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
              <img class="product-card__image" src="assets/icons/add-square-svgrepo-com.svg">
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
              <img class="product-card__image" src="assets/icons/edit-svgrepo-com.svg">
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
  .product-card__image {
      object-fit: contain;
      width: 25px;
      height: 25px;
      cursor: pointer;
      border-radius: 3px;
  }
  .product-card__image:hover {
      border: 1px solid white;
      width: 23px;
      height: 23px;
  }
  .product-card__image:active {
      border: 2px solid white;
      width: 21px;
      height: 21px;
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
