package com.codecool.service;

import com.codecool.model.Coin;
import com.codecool.model.Product;
import com.codecool.model.Stock;

import java.util.Map;

public class CancelService {
    private final Stock stock;
    private Map<Coin, Integer> insertedCoins;
    private Map<Product, Integer> orderedProducts;

    public CancelService(Stock stock, Map<Coin, Integer> insertedCoins, Map<Product, Integer> orderedProducts) {
        this.stock = stock;
        this.insertedCoins = insertedCoins;
        this.orderedProducts = orderedProducts;
    }

    public void cancelTransaction() {
        resetOrderedProducts();
        returnAllInsertedCoins();
    }

    public void resetOrderedProducts() {
        returnOrderedProductsToStock();
        clearProductsFromMap();
    }

    public void returnOrderedProductsToStock() {
        orderedProducts.forEach((product, quantity) -> stock.addToStock(product, quantity));
    }

    public void clearProductsFromMap() {
        orderedProducts.clear();
    }

    public void returnAllInsertedCoins() {
        insertedCoins.clear();
    }

}
