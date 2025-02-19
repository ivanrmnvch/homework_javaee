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
    private boolean active;

    public Product() {
        id = "";
        name = "";
        description = "";
        price = "";
        imagePath = "";
        brand = "";
        category = "";
        active = false;
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
        active = false;
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
        active = false;
    }

    public Product(
            String id,
            String name,
            String description,
            String price,
            String imagePath,
            String brand,
            String category,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.brand = brand;
        this.category = category;
        this.active = active;

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
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean getActive() {
        return active;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public boolean productIsValid() {
        return (
            (name != null && !name.isEmpty()) &&
            (description != null && !description.isEmpty()) &&
            (price != null && !price.isEmpty()) &&
            (imagePath != null && !imagePath.isEmpty()) &&
            (brand != null && !brand.isEmpty()) &&
            (category != null && !category.isEmpty())
        );
    }
    public String[][] getEditPageData() {
        return new String[][]{
                {"ID", "id", id},
                {"Название: ", "name", name},
                {"Описание: ", "description", description},
                {"Цена: ", "price", price},
                {"Url картинки: ", "imagePath", imagePath},
                {"Бренд: ", "brand", brand},
                {"Категория: ", "category", category},
                {"Active", "active", Boolean.toString(active)}
        };
    }
}
