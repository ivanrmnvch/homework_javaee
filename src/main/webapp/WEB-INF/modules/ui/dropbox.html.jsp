<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="custom-select">
  <div class="custom-select__input" onclick="openDropDownList()">
    <div class="custom-select__input-content">
      <div>
        10
      </div>
      <div>
        &#9660;
      </div>
    </div>
  </div>
  <div id="drop_down_list" class="drop-down-list">
    100
  </div>
</div>
<script>
  const openDropDownList = () => {
    document.querySelector('#drop_down_list').classList.toggle('drop-down-list__open');
  }
</script>
<style>
  .custom-select{
    margin: 16px;
    height: fit-content;
    width: fit-content;
  }
  .custom-select__input {
    background-color: white;
    height: 27px;
    width: 55px;
    border-radius: 3px;
    box-shadow: 2px 2px 5px -1px gray;
    display: flex;
    justify-content: center;
    flex-direction: column;
  }
  .custom-select__input-content {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    font-family: Arial, sans-serif;
    font-size: 14px;
  }
  .drop-down-list {
    display: none;
    border-radius: 3px;
    width: 45px;
    height: 50px;
    background-color: white;
    position: relative;
    top: 5px;
    z-index: 10;
    box-shadow: 2px 2px 5px -1px gray;
  }
  .drop-down-list__open {
    display: block;
  }
</style>