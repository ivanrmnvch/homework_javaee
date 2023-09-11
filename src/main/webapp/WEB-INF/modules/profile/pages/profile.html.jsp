<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
  <div class="profile col">
    <jsp:include page="../../ui/header.html.jsp"></jsp:include>
    <div class="row">
      <p>Логин:</p>
      <p>${user.getLogin()}</p>
    </div>
    <div class="row">
      <p>Email:</p>
      <p>${user.getEmail()}</p>
    </div>
    <div class="row">
      <p>Роль:</p>
      <p>${user.getRole()}</p>
    </div>
  </div>
</body>
</html>
<style>
  .profile {
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
  }
</style>
