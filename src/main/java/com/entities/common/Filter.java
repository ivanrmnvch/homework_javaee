package com.entities.common;

import java.io.Serializable;

public class Filter implements Serializable {
    private String name;
    private Price price;
    private String brand;
    private String category;

    public Filter() {
        name = "";
        price = new Price();
        brand = "";
        category = "";
    }
    public Filter(
        String name,
        Price price,
        String brand,
        String category
    ) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Price getPrice() {
        return this.price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
