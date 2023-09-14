package com.responses.products.search;

import com.entities.store.data.Product;

import java.io.Serializable;

public class Response implements Serializable {
    private Product product;
    private boolean success;
    private String message;

    public Response() {
        product = new Product();
        success = false;
        message = "";
    }

    public Response(Product product, boolean success, String message) {
        this.product = product;
        this.success = success;
        this.message = message;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public boolean getSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}