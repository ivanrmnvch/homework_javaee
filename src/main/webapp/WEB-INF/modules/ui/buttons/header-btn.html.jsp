<form
  method="GET"
  action="${param.action}"
>
  <button
    class="header-btn-wrapper ml-4"
    type="submit"
  >
    <jsp:include page="../../ui/image.html.jsp">
      <jsp:param name="imagePath" value="${param.imagePath}"/>
      <jsp:param name="iconHeight" value="25"/>
      <jsp:param name="iconWidth" value="25"/>
      <jsp:param name="imageBtn" value="true"/>
    </jsp:include>
  </button>
</form>