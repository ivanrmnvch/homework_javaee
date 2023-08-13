<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="app value-selector no-select">
  <div id="value_selector" class="value-selector__input">
    <div class="value-selector__input-content">
      <div>
        ${param.limit}
      </div>
      <div id="arrow" class="value-selector__arrow" onclick="switchDropDownList()">
        &#9660;
      </div>
    </div>
  </div>
  <div id="drop_down_list" class="drop-down-list">
    <form
      method="GET"
      action="products"
    >
      <button type=submit name="limit" value="10" class="drop-down-list__item" >10</button>
      <button type=submit name="limit" value="20" class="drop-down-list__item" >20</button>
      <button type=submit name="limit" value="50" class="drop-down-list__item" >50</button>
      <button type=submit name="limit" value="100" class="drop-down-list__item" >100</button>
    </form>
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
      dropDownList.classList.add('drop-down-list__close');
      dropDownList.classList.remove('drop-down-list__open');
      // удалям слушатель событий
      document.removeEventListener('click', hitTest);
      // переворачиваем иконку
      document.querySelector('#arrow').classList.toggle('value-selector__arrow-pressed');
    }
  };

  const switchDropDownList = () => {
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

    // получение количества классов. При первом открытии будет один класс, значит список закрыт
    const oneClass = dropDownList.classList.length === 1;

    if (oneClass) {
      dropDownList.classList.add('drop-down-list__open');
    } else if (dropDownListIsOpen) {
      dropDownList.classList.add('drop-down-list__close');
      dropDownList.classList.remove('drop-down-list__open');
    } else {
      dropDownList.classList.add('drop-down-list__open');
      dropDownList.classList.remove('drop-down-list__close');
    }
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
    flex-direction: column;
    background-color: white;
    border-radius: 3px;
    width: 55px;
    height: 104px;
    position: absolute;
    z-index: 10;
    box-shadow: 2px 2px 5px -1px gray;
    opacity: 0;
    animation: open-drop-down-list .2s forwards;
  }
  @keyframes open-drop-down-list {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
  .drop-down-list__open {
    display: flex;
  }
  .drop-down-list__close {
    display: flex;
    animation: close-drop-down-list .2s forwards;
  }
  @keyframes close-drop-down-list {
    0% {
      opacity: 1;
    }
    100% {
      display: none;
      opacity: 0;
    }
  }
  .drop-down-list__item {
    cursor: pointer;
    width: 47px;
    height: 18px;
    padding: 0 0 0 5px;
    margin: 4px;
    transition: all .1s;
    font-family: Arial, sans-serif;
    font-size: 14px;
    line-height: 18px;
    border: none;
    text-align: start;
    background-color: white;
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