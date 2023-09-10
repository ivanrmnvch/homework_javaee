<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <div class="content">
    <form
      class="content-item"
      method="GET"
      action="products"
    >
      <button
        type="submit"
        class="content-item__title">
      Store
      </button>
    </form>
    <div
      class="content-item"
      style="justify-content: end;"
    >
      <h4 class="header__user-name">
        ${user.getText()}
      </h4>
      <c:choose>
        <c:when test="${user.getUserIsAuthorized()}">
          <div style="position: relative;">
            <form
              method="GET"
              action="basket"
            >
              <button
                class="basket-btn ml-4"
                type="submit"
              >
                <img class="basket" src="assets/icons/cart-shopping-svgrepo-com.svg">
              </button>
            </form>
            <div class="counter">${cart.getCount()}</div>
          </div>
        </c:when>
      </c:choose>
      <form
        class="reset ml-4"
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
  </div>
</header>
<style>
  .content {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 800px;
  }
  .content-item {
      display: flex;
      align-items: center;
  }

  .content-item__title {
      color: gray;
      cursor: pointer;
      border: none;
      background-color: #e5e5e5;
      font-size: 20px;
      font-weight: bold;
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

  .header__user-name {
      color: black;
  }

  .basket-btn {
      border: none;
      background-color: #e5e5e5;
  }

.basket {
    object-fit: contain;
    width: 25px;
    height: 25px;
    cursor: pointer;
    border-radius: 3px;
}

.counter {
    color: black;
    background-color: white;
    height: 16px;
    width: 16px;
    border-radius: 8px;
    text-align: center;
    position: absolute;
    top: 13px;
    left: 38px;
    border: 1px solid black;
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
