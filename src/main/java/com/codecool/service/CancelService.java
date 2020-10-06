package com.codecool.service;

import com.codecool.model.Product;
import com.codecool.model.Stock;

import java.util.HashMap;
import java.util.Map;

public class CancelService {
    private Stock stock;
    private Map<Product, Integer> orderedProducts;
    public CancelService(Stock stock, Map<Product, Integer> orderedProducts) {
        this.stock = stock;
        this.orderedProducts = orderedProducts;
    }

    public void cancelTransaction(){
        resetOrderedProducts();
        returnAllInsertedCoins();
    }

    public void resetOrderedProducts() {
        returnOrderedProductsToStock();
        clearProductsFromMap();
    }

    private void clearProductsFromMap() {
        orderedProducts = new HashMap<>();
    }

    private void returnOrderedProductsToStock() {
        orderedProducts.forEach((product, quantity) -> stock.addToStock(product, quantity));
    }

    public void returnAllInsertedCoins(){

    }

}
