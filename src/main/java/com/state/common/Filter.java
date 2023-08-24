package com.state.common;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;

import java.io.Serializable;
import java.util.Objects;

@Stateful
public class Filter implements Serializable {
    private String name;
    @EJB
    private Price price;
    private String brand;
    private String category;
    private String categories;
    private String brands;

    public Filter() {
        name = "";
        price = new Price();
        brand = "";
        category = "";
        categories = "";
        brands = "";
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
        if (brand == null) {
            return;
        }

        if (brand.equals("all")) {
            this.brand = "";
            return;
        }

        String[] brandList = brands.split(",");

        for (String brandName : brandList) {
            if (brandName.equals(brand)) {
                this.brand = brand;
                return;
            }
        }
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        if (category == null) {
            return;
        }

        if (category.equals("all")) {
            this.category = "";
            return;
        }

        String[] categoryList = categories.split(",");

        System.out.println("CATEGORY: " + category);

        for (String categoryName : categoryList) {
            System.out.println("CATEGORY NAME: " + categoryName);
            if (categoryName.equals(category)) {
                this.category = category;
                return;
            }
        }
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    public String getBrands() {
        return brands;
    }
    public void setBrands(String brands) {
        this.brands = brands;
    }
}
