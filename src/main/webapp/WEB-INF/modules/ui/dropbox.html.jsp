<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="app value-selector no-select">
  <div id="value_selector" class="value-selector__input">
    <div class="value-selector__input-content">
      <div>
        10
      </div>
      <div id="arrow" class="value-selector__arrow"onclick="openDropDownList()">
        &#9660;
      </div>
    </div>
  </div>
  <div id="drop_down_list" class="drop-down-list">
    <div class="drop-down-list__item">10</div>
    <div class="drop-down-list__item" >20</div>
    <div class="drop-down-list__item" >50</div>
    <div class="drop-down-list__item" >100</div>
  </div>
</div>
<script>
  const hitTest = (e) => {
    // получение выпадающего списка и селектора
    const dropDownList = document.querySelector('#drop_down_list');
    const valueSelector = document.querySelector('#value_selector');

    // проверка открыт ли выпадающий список
    const dropDownListIsOpen = dropDownList.classList.contains('drop-down-list__open');

    // проверка нажатия на выпадающий список
    const clickedOnTheDropDownList = e.composedPath().includes(dropDownList);
    const clickedOnTheValueSelector = e.composedPath().includes(valueSelector);

    // проверка попадания по селектору и списку
    const hit = clickedOnTheValueSelector || clickedOnTheDropDownList;

    // если мы не попали по выпадающему списку и селектору и выпадающий список открыт, то
    if (!hit && dropDownListIsOpen) {
      // закрываем выпадающий список
      dropDownList.classList.toggle('drop-down-list__open');
      // удалям слушатель событий
      document.removeEventListener('click', hitTest);
      // переворачиваем иконку
      document.querySelector('#arrow').classList.toggle('value-selector__arrow-pressed');
    }
  };

  const openDropDownList = () => {
    // переворачивание иконки
    document.querySelector('#arrow').classList.toggle('value-selector__arrow-pressed');

    // получение выпадающего списка
    const dropDownList = document.querySelector('#drop_down_list');

    // проверка открыт ли выпадающий список
    const dropDownListIsOpen = dropDownList.classList.contains('drop-down-list__open');

    // если выпадающий список не открыт расчитываем координаты для его спавна +
    // добавляем проверку нажатий на странице
    if (!dropDownListIsOpen) {

      // получение позиции селектора
      const valueSelector = document.querySelector('#value_selector').getBoundingClientRect();
      const valueSelectorHeight = 27;
      const offsetY = 5;

      // координаты для спавна списка
      const dropDownListX = valueSelector.left + window.scrollX;
      const dropDownListY = valueSelector.top + window.scrollY + valueSelectorHeight + offsetY;

      // навешиваем координаты на список
      dropDownList.style.left = dropDownListX;
      dropDownList.style.top = dropDownListY;

      // проверка нажатий
      document.addEventListener("click", hitTest);
    }

    // открываем/закрываем список
    dropDownList.classList.toggle('drop-down-list__open');
  };

</script>
<style>
  .value-selector {
    margin: 16px;
    height: fit-content;
    width: fit-content;
  }
  .value-selector__input {
    background-color: white;
    height: 27px;
    width: 55px;
    border-radius: 3px;
    box-shadow: 2px 2px 5px -1px gray;
    display: flex;
    justify-content: center;
    flex-direction: column;
  }
  .value-selector__input-content {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    font-family: Arial, sans-serif;
    font-size: 14px;
  }
  .value-selector__arrow {
    cursor: pointer;
    transition: all .1s;
  }
  .value-selector__arrow-pressed {
    transform: rotate(180deg);
  }
  .drop-down-list {
    display: none;
    border-radius: 3px;
    width: 55px;
    height: 104px;
    background-color: rgba(255, 255, 255, 0);
    position: absolute;
    z-index: 10;
    box-shadow: 2px 2px 5px -1px gray;
    transition: background-color 5s linear;
  }
  .drop-down-list__open {
    display: flex;
    flex-direction: column;
    background-color: white;
  }
  .drop-down-list__item {
    cursor: pointer;
    width: 42px;
    height: 18px;
    padding-left:5px;
    margin: 4px;
    transition: all .1s;
    font-family: Arial, sans-serif;
    font-size: 14px;
    line-height: 18px;
  }
  .drop-down-list__item:hover {
    background-color: rgba(175, 218, 252, 0.5);
    border-radius: 3px;
  }
  .drop-down-list__item:active {
    background-color: rgba(175, 218, 252, 0.75);
    width: 40px;
    height: 16px;
    font-size: 12px;
    margin: 5px;
  }
</style>