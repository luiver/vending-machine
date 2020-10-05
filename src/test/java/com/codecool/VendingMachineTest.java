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
        assertEquals(1, vendingMachine.getInsertedCoins().get(Coin.DIME));
    }

    @Test
    void shouldUpdateCoinsAmount_whenSecondSameCoinInserted(){
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.DIME);
        assertEquals(2, vendingMachine.getInsertedCoins().get(Coin.DIME));
    }

    @Test
    void shouldAddNewProductIntoOrderedProducts_whenProductOrdered(){
        vendingMachine.getStock().addToStock(Product.COLA, 1);
        vendingMachine.orderProduct(Product.COLA);
        assertEquals(1, vendingMachine.getOrderedProducts().get(Product.COLA));
    }

    @Test
    void shouldUpdateProductAmount_whenSecondSameProductOrdered(){
        vendingMachine.getStock().addToStock(Product.COLA, 5);
        vendingMachine.orderProduct(Product.COLA);
        vendingMachine.orderProduct(Product.COLA);
        assertEquals(2,vendingMachine.getOrderedProducts().get(Product.COLA));
    }

    @Test
    void shouldNotAddMoreProductToOrderedProducts_whenProductOutOfStock(){
        vendingMachine.getStock().addToStock(Product.COLA, 1);
        vendingMachine.orderProduct(Product.COLA);
        vendingMachine.orderProduct(Product.COLA);
        assertEquals(1,vendingMachine.getOrderedProducts().get(Product.COLA));
    }
}