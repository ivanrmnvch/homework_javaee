package com.state.common;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;

import java.io.Serializable;
@Stateful
public class Filter implements Serializable {
    private String name;
    @EJB
    private Price price;
    private String brand;
    private String category;

    public Filter() {
        name = "";
        price = new Price();
        brand = "";
        category = "";
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
