<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String action = "auth-form";
  String btnName = "Login";
  String className = "login-btn";
  Cookie[] cookies = request.getCookies();
  for (Cookie c: cookies) {
    if ("user".equals(c.getName())) {
      action = "logout";
      btnName = "Logout";
      className = "logout-btn";
      break;
    }
  }
  request.setAttribute("action", action);
  request.setAttribute("btnName", btnName);
  request.setAttribute("className", className);
%>
<header class="app-center">
  <div class="app-width content">
    <form
      class="reset"
      method="GET"
      action="${action}"
    >
      <button
        class="${className}"
        type="submit"
      >
        ${btnName}
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

  .logout-btn {
      border: none;
      border-radius: 3px;
      cursor: pointer;
      color: white;
      font-size: 16px;
      background-color: #ce35c8;
      padding: 5px 20px;
  }

  .logout-btn:hover {
      background-color: #bf44ba;
  }

  .logout-btn:active {
      background-color: #a643a1;
  }
</style>
