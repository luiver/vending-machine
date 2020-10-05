package com.codecool;

import com.codecool.model.*;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Stock stock;
    private Wallet machineWallet;
    private Map<Coin, Integer> insertedCoins;
    private Map<Product, Integer> orderedProducts;

    public VendingMachine() {
        stock = new Stock();
        machineWallet = new MachineWallet();
        insertedCoins = new HashMap<>();
        orderedProducts = new HashMap<>();
    }

    public void init(){
        stock.populateStock();
        machineWallet.populateWallet();
    }

    public boolean insertCoin(Coin coin){
        if (checkIfInsertedCoinIsValid(coin)) {
            updateInsertedCoinsAmount(coin);
            return true;
        }
        return false;
    }

    private void updateInsertedCoinsAmount(Coin coin) {
        if (insertedCoins.get(coin) != null) {
            insertedCoins.computeIfPresent(coin, (k, v) -> v + 1);
        } else {
            insertedCoins.put(coin, 1);
        }
    }

    private boolean checkIfInsertedCoinIsValid(Coin coin) {
        return !coin.equals(Coin.PENNY);
    }

    public boolean orderProduct(Product product){
        if (checkIfProductSoldOut(product)) {
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

    }

    public void resetOrderedProducts(){

    }

    public void returnAllInsertedCoins(){

    }

    public void countChange(){

    }

    public void giveChange(){

    }

    public double countTotalValueOfInsertedCoins(){
        return 0.00;
    }

    public double countTotalPriceOfOrderedProducts(){
        return 0.00;
    }

    public boolean checkIfProductSoldOut(Product product) {
        return stock.getProductsOnStock().get(product) == 0;
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
