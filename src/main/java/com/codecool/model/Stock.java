package com.codecool.model;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<Product, Integer> productsOnStock;

    public Stock() {
        this.productsOnStock = new HashMap<>();
    }

    public void populateStock() {
        productsOnStock.put(Product.COLA, 999);
        productsOnStock.put(Product.CHIPS, 999);
        productsOnStock.put(Product.CANDY, 999);
    }

    public boolean checkIfSufficientProductsOnStock(Product product, int quantity) {
        return (productsOnStock.get(product) - quantity) >= 0;
    }

    public boolean checkIfProductOnStock(Product product) {
        return productsOnStock.get(product) > 0;
    }

    public void removeFromStock(Product product, int quantity){
        productsOnStock.computeIfPresent(product, (k, v) -> v - quantity);
    }

    public void printStock(){
        System.out.println("\nStock:");
        System.out.println("Product: \tAmount:");
        productsOnStock.forEach((k, v) -> System.out.println(k + "\t\t" + v));
    }
}
