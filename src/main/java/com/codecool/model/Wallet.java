package com.codecool.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Wallet {
    protected Map<Coin, Integer> coins;

    public Wallet() {
        this.coins = new HashMap<>();
    }

    public abstract void populateWallet();

    public boolean checkIfSufficientCoinsInWallet(Coin coin, int quantity) {
        return (coins.get(coin) - quantity) >= 0;
    }

    public boolean checkIfCoinInWallet(Coin coin) {
        return coins.get(coin) > 0;
    }

    public void removeFromWallet(Coin coin, int quantity){
        coins.computeIfPresent(coin, (k, v) -> v - quantity);
    }

    public void addToWallet(Coin coin, int quantity){
        if (checkIfCoinInWallet(coin)) {
            coins.computeIfPresent(coin, (k, v) -> v + quantity);
        } else {
            coins.put(coin,quantity);
        }
    }

    public abstract void printWallet();

    public Map<Coin, Integer> getCoins() {
        return coins;
    }
}
