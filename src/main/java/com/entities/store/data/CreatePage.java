package com.entities.store.data;

import java.io.Serializable;

public class CreatePage implements Serializable {
    private String[][] data;

    public CreatePage() {
        data = new String[][]{
            {"Название: ", "name"},
            {"Описание: ", "description"},
            {"Цена: ", "price"},
            {"Url картинки: ", "imagePath"},
            {"Бренд: ", "brand"},
            {"Категория: ", "category"},
        };
    }
    public String[][] getData() {
        return data;
    }
    public void setData(String[][] data) {
        this.data = data;
    }
}
