<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="app-center">
  <div class="app-width content">
    <form
      class="reset"
      method="GET"
      action="registration"
    >
      <button
        class="login-btn"
        type="submit"
      >
        Login
      </button>
    </form>
  </div>
</header>
<style>
  .content {
    text-align: end;
  }

  header {
      height: 40px;
      background-color: #e5e5e5;
      color: #fff;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
  }

  .login-btn {
      border: none;
      border-radius: 3px;
      cursor: pointer;
      color: white;
      font-size: 16px;
      background-color: #4c9a50;
      padding: 5px 20px;
  }

  .login-btn:hover {
      background-color: #45a049;
  }

  .login-btn:active {
      background-color: #51be56;
  }
</style>
