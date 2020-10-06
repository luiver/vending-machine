package com.codecool.service;

import com.codecool.model.Coin;
import com.codecool.model.Product;

import java.util.Map;

public class ChangeService {
    private Map<Coin, Integer> insertedCoins;
    private Map<Product, Integer> orderedProducts;

    public ChangeService(Map<Coin, Integer> insertedCoins, Map<Product, Integer> orderedProducts) {
        this.insertedCoins = insertedCoins;
        this.orderedProducts = orderedProducts;
    }

    public boolean checkIfCanAffordForPurchase(){
        return (countChangeToGive()) >= 0;
    }

    public boolean checkIfNeedToGiveTheChange(){
        return (countChangeToGive()) > 0;
    }

    public double countChangeToGive() {
        return countTotalValueOfInsertedCoins() - countTotalPriceOfOrderedProducts();
    }

    public void giveChange(){

    }

    public double countTotalValueOfInsertedCoins(){
        double total = 0.00;
        for (Map.Entry<Coin, Integer> entry : insertedCoins.entrySet()) {
            double coinValue = entry.getKey().getValue();
            int numberOfCoins = entry.getValue();
            total += coinValue * numberOfCoins;
        }
        return total;
    }

    public double countTotalPriceOfOrderedProducts(){
        double total = 0.00;
        for (Map.Entry<Product, Integer> entry : orderedProducts.entrySet()) {
            double productPrice = entry.getKey().getPrice();
            int numberOfProducts = entry.getValue();
            total += productPrice * numberOfProducts;
        }
        return total;
    }

    public void exactChangeOnly(){

    }
}
