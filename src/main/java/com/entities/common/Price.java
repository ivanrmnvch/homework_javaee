package com.entities.common;

import java.io.Serializable;

public class Price implements Serializable {
    private String min;
    private String max;

    public Price() {
        min = "0";
        max = "300000";
    }

    public Price(String min, String max) {
        this.min = min;
        this.max = max;
    }

    public String getMin() {
        return min;
    }
    public void setMin(String min) {
        this.min = min;
    }
    public String getMax() {
        return max;
    }
    public void setMax(String max) {
        this.max = max;
    }
}
