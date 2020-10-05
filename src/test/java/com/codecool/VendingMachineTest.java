package com.codecool;

import com.codecool.model.Coin;
import com.codecool.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {
    private static VendingMachine vendingMachine;

    @BeforeAll
    static void init(){
        vendingMachine = new VendingMachine();
    }

    @Test
    void shouldReturnFalse_whenInvalidCoinInserted(){
        vendingMachine.insertCoin(Coin.PENNY);
        assertEquals(vendingMachine.getStock().getProductsOnStock().get(Product.COLA),1);
    }

    @Test
    void shouldReturnTrue_whenCoinInserted(){
        vendingMachine.insertCoin(Coin.DIME);
        assertEquals(vendingMachine.getMachineWallet().getCoins().get(Coin.DIME),1);
    }
}