<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Reg</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>

<body>
<div class="container">
  <div class="form-group">
    <div class="form-group__row">
      <jsp:include page="../ui/buttons/back-btn.html.jsp">
        <jsp:param name="action" value="auth-form"/>
      </jsp:include>
      <jsp:include page="../ui/buttons/return-btn.html.jsp">
        <jsp:param name="action" value="/homework_javaee-1.0-SNAPSHOT"/>
      </jsp:include>
    </div>
    <h2>Registration</h2>
  </div>
  <form action="registration" method="post">
    <div class="form-group">
      <div class="group-item">
        <div>
          <label for="email">Email</label>
        </div>
        <div>
          <input value="${email}" type="email" id="email" name="email" required>
        </div>
      </div>
      <div class="group-item">
        <div>
          <label for="username">Login</label>
        </div>
        <div>
          <input value="${userName}" type="text" id="username" name="username" required>
        </div>
      </div>
      <div class="group-item">
        <div>
          <label for="password">Pass</label>
        </div>
        <div>
          <input type="password" id="password" name="password" required>
        </div>
      </div>
    </div>
    <jsp:include page="../ui/buttons/btn.html.jsp">
      <jsp:param name="btnText" value="Create an account"/>
      <jsp:param name="btnStyle" value="green"/>
    </jsp:include>
  </form>
</div>
</body>

<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
  }

  .container {
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    padding: 20px;
    width: 300px;
  }

  h2 {
    margin: 0 0 20px 0;
    text-align: center;
  }

  .form-group {
    display: flex;
    flex-direction: column;
  }

  .form-group__row {
      display: flex;
      justify-content: space-between;
      align-items: center;
  }

  .group-item {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
  }

  label {
    font-weight: bold;
    cursor: pointer;
  }

  input[type="email"],
  input[type="text"],
  input[type="password"] {
    width: 200px;
    padding: 10px;
    border-radius: 3px;
    border: 1px solid #ccc;
  }
</style>
</html>

