<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div
  style="height: ${param.iconHeight}px; width: ${param.iconWidth}px;"
>
  <c:choose>
    <c:when test="${param.imageBtn}">
      <img
        class="image-wrapper image-btn"
        style="height: ${param.iconHeight}px; width: ${param.iconWidth}px;"
        src="${param.imagePath}"
      >
    </c:when>
    <c:otherwise>
      <img
        class="image-wrapper"
        style="height: ${param.iconHeight}px; width: ${param.iconWidth}px;"
        src="${param.imagePath}"
      >
    </c:otherwise>
  </c:choose>
</div>