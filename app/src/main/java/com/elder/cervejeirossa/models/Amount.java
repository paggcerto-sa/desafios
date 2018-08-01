package com.elder.cervejeirossa.models;

import java.io.Serializable;

public class Amount implements Serializable {

    private double value;

    private String unit;

    public Amount(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
