package com.codecool.model;

public enum Product {
    COLA(1.00),
    CHIPS(0.50),
    CANDY(0.65);

    private final double price;

    Product(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
