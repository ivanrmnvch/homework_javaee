<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="pagination no-select">
  <div>
    <button class="pagination__btn" >&#9668;</button>
    <button class="pagination__btn" >1</button>
    <button class="pagination__btn" >2</button>
    <button class="pagination__btn" >3</button>
    <button class="pagination__btn" >...</button>
    <button class="pagination__btn" >14</button>
    <button class="pagination__btn" >&#9658;</button>
  </div>
</div>
<style>
  .pagination {
    display: flex;
    flex-direction: row;
    margin: 16px;
    align-items: center;
    width: 256px;
    height: fit-content;
  }
  .pagination__btn {
    background-color: white;
    border: none;
    border-radius: 3px;
    margin: 0 3px;
    padding: 0;
    width: 27px;
    box-shadow: 2px 2px 5px -1px gray;
    height: 27px;
    font-size: 14px;
    transition: all .1s;
    cursor: pointer;
  }
  .pagination__btn:hover {
    background-color: rgba(175, 218, 252, 0.5);
  }
  .pagination__btn:active {
      background-color: rgba(175, 218, 252, 0.75);
      width: 25px;
      height: 25px;
      font-size: 12px;
      box-shadow: 3px 3px 5px -1px gray;
      margin: 0 4px;
  }
  .pagination__btn:not(:hover) {
      background-color: white;
      border: none;
      border-radius: 3px;
      margin: 0 3px;
      padding: 0;
      width: 27px;
      box-shadow: 2px 2px 5px -1px gray;
      height: 27px;
      font-size: 14px;
  }
</style>
