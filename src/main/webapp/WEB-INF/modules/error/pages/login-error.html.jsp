<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>404</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
<div class="center-app">
  <div class="content-wrapper error-content col">
    <div class="error-btn-return-wrapper">
      <div class="error-btn-return">
        <jsp:include page="../../ui/buttons/return-btn.html.jsp">
          <jsp:param name="action" value="products"/>
        </jsp:include>
      </div>
    </div>
    <div class="error-text">
      Ошибка авторизации!
    </div>
  </div>
</div>
</body>
</html>
