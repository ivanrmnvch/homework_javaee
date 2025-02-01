<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="basket-card content-wrapper row">
  <jsp:include page="../../ui/image.html.jsp">
    <jsp:param name="imagePath" value="${param.imagePath}"/>
    <jsp:param name="iconHeight" value="70"/>
    <jsp:param name="iconWidth" value="97"/>
  </jsp:include>
  <p class="basket-card__text col basket-card__name ml-4">
    ${param.name}
  </p>
  <p class="basket-card__text col basket-card__price ml-4">
    ${param.price}
    &#8381;
  </p>
    <button
      class="basket-card__btn-delete"
      onclick="deleteProduct(${param.id})"
    >
      <jsp:include page="../../ui/image.html.jsp">
        <jsp:param name="imagePath" value="assets/icons/cart-xmark-svgrepo-com.svg"/>
        <jsp:param name="iconHeight" value="20"/>
        <jsp:param name="iconWidth" value="20"/>
        <jsp:param name="imageBtn" value="true"/>
      </jsp:include>
    </button>
</div>
<script>
  const deleteProduct = (productId) => {
    window.open('/homework_javaee-1.0-SNAPSHOT/basket/delete?productId=' + productId, '_self');
  };
</script>