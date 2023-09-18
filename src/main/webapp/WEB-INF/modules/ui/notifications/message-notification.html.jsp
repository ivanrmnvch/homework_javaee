<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Authorization error</title>
  <link rel="stylesheet" href="assets/css/index.css" type="text/css">
</head>
<body class="app">
<div class="center-app">
  <div class="content-wrapper message-notification">
    <div class="error-btn-return-wrapper">
      <div class="error-btn-return">
        <jsp:include page="../buttons/return-btn.html.jsp">
          <jsp:param name="action" value="products"/>
        </jsp:include>
      </div>
    </div>
    <div class="col message-content">
      <div class="message-text mt-4">
        ${message}
      </div>
      <form
        method="GET"
        action="${action}"
      >
        <jsp:include page="../buttons/btn.html.jsp">
          <jsp:param name="btnText" value="${btnText}"/>
          <jsp:param name="btnStyle" value="green"/>
          <jsp:param name="btnHeight" value="35"/>
          <jsp:param name="btnWidth" value="175"/>
        </jsp:include>
      </form>
    </div>
  </div>
</div>
</body>
</html>
