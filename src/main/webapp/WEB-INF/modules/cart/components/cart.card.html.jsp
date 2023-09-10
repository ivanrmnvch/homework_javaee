<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="basket-card">
  <img class="basket-card__image" src="${param.imagePath}">
  <p class="basket-card__text basket-card__name ml-4">
    ${param.name}
  </p>
  <p class="basket-card__text basket-card__price ml-4">
    ${param.price}
    &#8381;
  </p>
    <button
      class="basket-card-btn"
      onclick="deleteProduct(${param.id})"
    >
      <img class="basket-card__delete" src="assets/icons/cart-xmark-svgrepo-com.svg">
    </button>
</div>
<script>
  const deleteProduct = (productId) => {
    window.open('/homework_javaee-1.0-SNAPSHOT/basket/delete?productId=' + productId, '_self');
  };
</script>
<style>
  .basket-card {
      margin: 10px;
      padding: 10px;
      background-color: white;
      box-shadow: 2px 2px 5px -1px gray;
      width: 500px;
      border-radius: 8px;
      height: 70px;
      display: flex;
      flex-direction: row;
  }
  .basket-card__text {
      display: flex;
      flex-direction: column;
      justify-content: center;
  }
</style>