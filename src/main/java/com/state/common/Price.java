package com.state.common;

import jakarta.ejb.Stateful;

import java.io.Serializable;
@Stateful
public class Price implements Serializable {
    private String min;
    private String max;

    public Price() {
        min = "0";
        max = "300000";
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
