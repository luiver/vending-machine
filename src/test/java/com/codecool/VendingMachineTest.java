package com.codecool;

import com.codecool.model.Coin;
import com.codecool.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {
    private VendingMachine vendingMachine;

    @BeforeEach
    void beforeEach() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void shouldReturnFalse_whenInvalidCoinInserted(){
        assertFalse(vendingMachine.insertCoin(Coin.PENNY));
    }

    @Test
    void shouldReturnNull_whenInvalidCoinInserted(){
        vendingMachine.insertCoin(Coin.PENNY);
        assertEquals(vendingMachine.getInsertedCoins().get(Coin.DIME),null);
    }

    @Test
    void shouldReturnTrue_whenCoinInserted(){
        vendingMachine.insertCoin(Coin.DIME);
        assertEquals(vendingMachine.getInsertedCoins().get(Coin.DIME),1);
    }
}