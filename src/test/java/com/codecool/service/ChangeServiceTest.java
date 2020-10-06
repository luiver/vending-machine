package com.codecool.service;

import com.codecool.VendingMachine;
import com.codecool.model.Coin;
import com.codecool.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangeServiceTest {
    private VendingMachine vendingMachine;

    @BeforeEach
    void beforeEach() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void shouldReturnTotalValueOfInsertedCoins_whenCoinsInserted(){
        vendingMachine.getInsertService().insertCoin(Coin.DOLLAR);
        vendingMachine.getInsertService().insertCoin(Coin.DOLLAR);
        vendingMachine.getInsertService().insertCoin(Coin.DIME);
        vendingMachine.getInsertService().insertCoin(Coin.NICKEL);
        vendingMachine.getInsertService().insertCoin(Coin.QUARTER);
        vendingMachine.getInsertService().insertCoin(Coin.QUARTER);
        vendingMachine.getInsertService().insertCoin(Coin.HALF);
        assertEquals(3.15, vendingMachine.getOrderService().getChangeService().countTotalValueOfInsertedCoins());
    }

    @Test
    void shouldReturnTrue_whenCanAffordBuyAllOrderedProducts(){
        vendingMachine.getStock().populateStock();
        vendingMachine.getInsertService().insertCoin(Coin.DOLLAR);
        vendingMachine.getInsertService().insertCoin(Coin.DOLLAR);
        vendingMachine.getInsertService().insertCoin(Coin.DIME);
        vendingMachine.getInsertService().insertCoin(Coin.NICKEL);
        vendingMachine.getInsertService().insertCoin(Coin.QUARTER);
        vendingMachine.getInsertService().insertCoin(Coin.QUARTER);
        vendingMachine.getInsertService().insertCoin(Coin.HALF);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        vendingMachine.getOrderService().orderProduct(Product.CANDY);
        vendingMachine.getOrderService().orderProduct(Product.CHIPS);
        assertTrue(vendingMachine.getOrderService().getChangeService().checkIfCanAffordForPurchase());
    }

    @Test
    void shouldReturnTrue_whenThereIsChangeToGive(){
        vendingMachine.getStock().populateStock();
        vendingMachine.getInsertService().insertCoin(Coin.DOLLAR);
        vendingMachine.getOrderService().orderProduct(Product.CHIPS);
        assertTrue(vendingMachine.getOrderService().getChangeService().checkIfNeedToGiveTheChange());
    }
    @Test
    void shouldReturnFalse_whenThereIsNoChangeToGive(){
        vendingMachine.getStock().populateStock();
        vendingMachine.getInsertService().insertCoin(Coin.DOLLAR);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        assertFalse(vendingMachine.getOrderService().getChangeService().checkIfNeedToGiveTheChange());
    }
}