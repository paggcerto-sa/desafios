package com.elder.cervejeirossa.models;

import java.io.Serializable;

public class Malt implements Serializable {

    private String name;

    private Amount amount;

    public Malt(String name, Amount amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
