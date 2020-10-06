package com.codecool.service;

import com.codecool.VendingMachine;
import com.codecool.model.Coin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertServiceTest {
    private VendingMachine vendingMachine;

    @BeforeEach
    void beforeEach() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void shouldReturnFalse_whenInvalidCoinInserted(){
        assertFalse(vendingMachine.getInsertService().insertCoin(Coin.PENNY));
    }

    @Test
    void shouldAddNewCoinIntoInsertedCoins_whenCoinInserted(){
        vendingMachine.getInsertService().insertCoin(Coin.DIME);
        assertEquals(1, vendingMachine.getInsertedCoins().get(Coin.DIME));
    }

    @Test
    void shouldUpdateCoinsAmount_whenSecondSameCoinInserted(){
        vendingMachine.getInsertService().insertCoin(Coin.DIME);
        vendingMachine.getInsertService().insertCoin(Coin.DIME);
        assertEquals(2, vendingMachine.getInsertedCoins().get(Coin.DIME));
    }
}