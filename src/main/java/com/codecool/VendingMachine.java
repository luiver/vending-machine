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

    public void insertCoin(Coin coin){

    }

    private boolean checkIfInsertedCoinIsValid(Coin coin){
        return false;
    }

    public void selectProduct(Product product){

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

    public void productSoldOut(){

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
