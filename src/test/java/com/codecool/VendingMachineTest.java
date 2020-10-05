package com.codecool;

import com.codecool.model.Coin;
import com.codecool.model.Product;
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
        assertNull(vendingMachine.getInsertedCoins().get(Coin.PENNY));
    }

    @Test
    void shouldAddNewCoinIntoInsertedCoins_whenCoinInserted(){
        vendingMachine.insertCoin(Coin.DIME);
        assertEquals(vendingMachine.getInsertedCoins().get(Coin.DIME),1);
    }

    @Test
    void shouldUpdateCoinsAmount_whenSecondSameCoinInserted(){
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.DIME);
        assertEquals(vendingMachine.getInsertedCoins().get(Coin.DIME),2);
    }

    @Test
    void shouldAddNewProductIntoOrderedProducts_whenProductOrdered(){
        vendingMachine.updateOrderedProducts(Product.COLA);
        assertEquals(vendingMachine.getOrderedProducts().get(Product.COLA),1);
    }

    @Test
    void shouldUpdateProductAmount_whenSecondSameProductOrdered(){
        vendingMachine.updateOrderedProducts(Product.COLA);
        vendingMachine.updateOrderedProducts(Product.COLA);
        assertEquals(vendingMachine.getOrderedProducts().get(Product.COLA),2);
    }

    @Test
    void shouldNotAddMoreProductToOrderedProducts_whenProductOutOfStock(){
        vendingMachine.getStock().addToStock(Product.COLA, 1);
        vendingMachine.updateOrderedProducts(Product.COLA);
        vendingMachine.updateOrderedProducts(Product.COLA);
        assertEquals(vendingMachine.getOrderedProducts().get(Product.COLA),1);
    }
}