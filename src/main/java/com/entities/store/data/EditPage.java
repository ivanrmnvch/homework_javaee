package com.entities.store.data;

import java.io.Serializable;

public class EditPage implements Serializable {
    private String[][] data;

    public EditPage() {
        data = new String[][]{
            {"Название: ", "searchValue"},
            {"Id товара: ", "productId"},
        };
    }
    public String[][] getData() {
        return data;
    }
    public void setData(String[][] data) {
        this.data = data;
    }
}
