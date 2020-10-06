package com.codecool;

import com.codecool.model.*;
import com.codecool.service.InsertService;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Stock stock;
    private Wallet machineWallet;
    private Map<Coin, Integer> insertedCoins;
    private Map<Product, Integer> orderedProducts;
    private InsertService insertService;

    public VendingMachine() {
        stock = new Stock();
        machineWallet = new MachineWallet();
        insertedCoins = new HashMap<>();
        orderedProducts = new HashMap<>();
        insertService = new InsertService(insertedCoins);
    }

    public void init(){
        stock.populateStock();
        machineWallet.populateWallet();
    }


    public boolean orderProduct(Product product){
        if (stock.checkIfProductSoldOut(product)) {
            return false;
        }
        updateOrderedProducts(product);
        return true;
    }


    private void updateOrderedProducts(Product product) {
        int quantity = 1;
        if (orderedProducts.get(product) != null) {
            orderedProducts.computeIfPresent(product, (k, v) -> v + quantity);
        } else {
            orderedProducts.put(product, quantity);
        }
        stock.removeFromStock(product, quantity);
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

    public Wallet getMachineWallet() {
        return machineWallet;
    }

    public Stock getStock() {
        return stock;
    }

    public Map<Coin, Integer> getInsertedCoins() {
        return insertedCoins;
    }

    public Map<Product, Integer> getOrderedProducts() {
        return orderedProducts;
    }
}
