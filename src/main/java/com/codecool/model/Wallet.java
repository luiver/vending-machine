package com.codecool.model;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private Map<Coin, Integer> coins;

    public Wallet() {
        this.coins = new HashMap<>();
    }

    public void populate() {
        coins.put(Coin.PENNY, 100);
        coins.put(Coin.NICKEL, 100);
        coins.put(Coin.DIME, 50);
        coins.put(Coin.QUARTER, 10);
        coins.put(Coin.HALF, 10);
        coins.put(Coin.DOLLAR, 10);
    }

    public boolean checkIfSufficientCoinsInWallet(Coin coin, int quantity) {
        return (coins.get(coin) - quantity) >= 0;
    }

    public boolean checkIfCoinInWallet(Coin coin) {
        return coins.get(coin) > 0;
    }

    public void removeFromWallet(Coin coin, int quantity){
        coins.computeIfPresent(coin, (k, v) -> v - quantity);
    }

    public void printWallet(){
        System.out.println("\nYour Wallet:");
        System.out.println("Coin: \t\tAmount:");
        coins.forEach((k, v) -> System.out.println(k + "\t\t" + v));
    }
}
