<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Auth</title>
</head>

<body>
<div class="container">
  <h2>Authorization</h2>
  <form
    action=""
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
    <button
      class="btn btn-sign-in"
      type="submit"
    >
      Sign in
    </button>
  </form>
  <form
    action="registration"
    method="GET"
  >
    <button
      class="btn btn-registration"
      type="submit"
    >
      Registration
    </button>
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

  .btn-sign-in {
    background-color: #4CAF50;
  }

  .btn-sign-in:hover {
    background-color: #45a049;
  }

  .btn-registration {
    background-color: #ce35c8;
  }

  .btn-registration:hover {
    background-color: #bf44ba;
  }
</style>
</html>
