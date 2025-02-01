package com.entities.store.data;

import java.io.Serializable;

public class CreatePage implements Serializable {
    private String[][] data;

    public CreatePage() {
        data = new String[][]{
            {"Название: ", "name", "text"},
            {"Описание: ", "description", "text"},
            {"Цена: ", "price", "number"},
            {"Url картинки: ", "imagePath", "url"},
            {"Бренд: ", "brand", "text"},
            {"Категория: ", "category", "text"},
        };
    }
    public String[][] getData() {
        return data;
    }
    public void setData(String[][] data) {
        this.data = data;
    }
}
