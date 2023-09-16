<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Auth</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>

<body>
<div class="container">
  <div class="title">
    <jsp:include page="../ui/buttons/return-btn.html.jsp">
      <jsp:param name="action" value="products"/>
    </jsp:include>
    <h2>Authorization</h2>
  </div>
  <form
    action="auth"
    method="GET"
  >
    <div class="form-group">
      <div class="group-item">
        <div>
          <label for="username">Login</label>
        </div>
        <div>
          <input
            id="username"
            type="text"
            name="username"
            required
          >
        </div>
      </div>
      <div class="group-item">
        <div>
          <label for="password">Pass</label>
        </div>
        <div>
          <input
            id="password"
            type="password"
            name="password"
            required
          >
        </div>
      </div>
    </div>
    <div class="auth-btn-green">
      <jsp:include page="../ui/buttons/btn.html.jsp">
        <jsp:param name="btnText" value="Sign in"/>
        <jsp:param name="btnStyle" value="green"/>
      </jsp:include>
    </div>
  </form>
  <form
    action="registration-form"
    method="GET"
  >
    <jsp:include page="../ui/buttons/btn.html.jsp">
      <jsp:param name="btnText" value="Registration"/>
      <jsp:param name="btnStyle" value="purple"/>
    </jsp:include>
  </form>
</div>
</body>

<style>
  .title {
      display: flex;
      flex-direction: column;
  }
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
    margin-top: 0;
    text-align: center;
  }
  .form-group {
    display: flex;
    flex-direction: column;
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

  input[type="text"],
  input[type="password"] {
    width: 200px;
    padding: 10px;
    border-radius: 3px;
    border: 1px solid #ccc;
  }
</style>
</html>
