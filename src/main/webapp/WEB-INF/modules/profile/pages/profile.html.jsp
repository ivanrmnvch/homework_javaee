<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
  <div class="center-app col">
    <jsp:include page="../../ui/header.html.jsp"></jsp:include>
    <div class="profile-page">
      <div class="row">
        <div>
          <img class="profile-icon" src="assets/icons/profile-circle-svgrepo-com.svg">
        </div>
        <div class="ml-5">
          <div class="row">
            <p>Логин: </p>
            <p>${user.getLogin()}</p>
          </div>
          <div class="row">
            <p>Email: </p>
            <p>${user.getEmail()}</p>
          </div>
          <div class="row">
            <p>Роль: </p>
            <p>${user.getRole()}</p>
          </div>
        </div>
      </div>
      <div class="col">
        <form
          method="GET"
          action="create"
        >
          <button
            type="submit"
            class="profile-create-btn">
            Создать
          </button>
        </form>
        <form
          onsubmit="test()"
        >
          <button
            type="submit"
            class="profile-edit-btn">
            Редактировать
          </button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
<script>
  const updateProduct = (id) => {
      window.open('/homework_javaee-1.0-SNAPSHOT/product/update' + id);
  };
</script>
<style>
  .profile-page {
      background-color: white;
      border-radius: 8px;
      width: 230px;
      height: 300px;
      box-shadow: 2px 2px 5px -1px gray;
      padding: 10px;
  }
  .profile-icon {
      object-fit: contain;
      width: 50px;
      height: 50px;
  }
  .profile-create-btn {
      border: none;
      border-radius: 3px;
      cursor: pointer;
      color: white;
      font-size: 16px;
      background-color: #4c9a50;
      padding: 5px 20px;
      margin: 10px 35px;
  }

  .profile-create-btn:hover {
      background-color: #45a049;
  }

  .profile-create-btn:active {
      background-color: #51be56;
  }
  .profile-edit-btn {
      border: none;
      border-radius: 3px;
      cursor: pointer;
      color: white;
      font-size: 16px;
      background-color: #ce35c8;
      padding: 5px 20px;
      margin: 10px 35px;
  }

  .profile-edit-btn:hover {
      background-color: #bf44ba;
  }

  .profile-edit-btn:active {
      background-color: #a643a1;
  }
</style>
