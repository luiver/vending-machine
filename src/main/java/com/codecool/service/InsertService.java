package com.codecool.service;

import com.codecool.model.Coin;

import java.util.Map;

public class InsertService {
    private Map<Coin, Integer> insertedCoins;

    public InsertService(Map<Coin, Integer> insertedCoins) {
        this.insertedCoins = insertedCoins;
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
}
