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
      <form
        action="auth-form"
        method="GET"
      >
        <input class="btn-back" type="submit" value="&#9668;" >
      </form>
      <form
        action="/homework_javaee-1.0-SNAPSHOT"
        method="GET"
      >
        <input class="btn-return" type="submit" value="&#10060;">
      </form>
    </div>
    <h2>A user with this email already exists</h2>
  </div>
  <form action="registration-form" method="get">
    <h4>Try registering again</h4>
    <button class="btn btn-registration" type="submit">Registration</button>
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
    height: 260px;
  }

  h2 {
    margin: 0 0 20px 0;
    text-align: center;
  }

  h4 {
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
      margin-bottom: 40px;
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

  .btn {
    border: none;
    color: white;
    margin-bottom: 10px;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    border-radius: 3px;
    cursor: pointer;
    width: 100%;
  }

  .btn-registration {
      background-color: #ce35c8;
  }

  .btn-registration:hover {
      background-color: #bf44ba;
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

  .btn-back {
      cursor: pointer;
      border: none;
      border-radius: 3px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      background-color: #f2f2f2;
  }
</style>
</html>

