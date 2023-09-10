package com.entities.store.data;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private String price;
    private String imagePath;
    private String brand;
    private String category;

    public Product() {
        id = "";
        name = "";
        description = "";
        price = "";
        imagePath = "";
        brand = "";
        category = "";
    }

    public Product(
        String id,
        String name,
        String price,
        String imagePath
    ) {
        this.id = id;
        this.name = name;
        description = "";
        this.price = price;
        this.imagePath = imagePath;
        brand = "";
        category = "";
    }
    public Product(
            String id,
            String name,
            String description,
            String price,
            String imagePath,
            String brand
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.brand = brand;
        this.category = "";
    }


    public Product(
            String id,
            String name,
            String description,
            String price,
            String imagePath,
            String brand,
            String category
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.brand = brand;
        this.category = category;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBrand() {
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
