package com.entities.store.data;

import java.io.Serializable;

public class Products implements Serializable {
  private String name;
  private String description;
  private String price;
  private String imagePath;
  private String brand;
  private String category;

  public Products() {
    name = "def";
    description = "def";
    price = "def";
    imagePath = "def";
    brand = "def";
    category = "def";
  }

  public Products(
    String name,
    String description,
    String price,
    String imagePath,
    String brand,
    String category
  ) {
    this.name = name;
    this.description = description;
    this.price = price;
    this. imagePath = imagePath;
    this.brand = brand;
    this.category = category;
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
