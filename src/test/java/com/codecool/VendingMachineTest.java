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

    @Test
    void shouldReturnTotalPriceOfOrderedProducts_whenProductsOrdered(){
        vendingMachine.getStock().populateStock();
        vendingMachine.orderProduct(Product.COLA);
        vendingMachine.orderProduct(Product.COLA);
        vendingMachine.orderProduct(Product.CANDY);
        vendingMachine.orderProduct(Product.CHIPS);
        assertEquals(3.15,vendingMachine.countTotalPriceOfOrderedProducts());
    }

    @Test
    void shouldReturnTotalValueOfInsertedCoins_whenCoinsInserted(){
        vendingMachine.insertCoin(Coin.DOLLAR);
        vendingMachine.insertCoin(Coin.DOLLAR);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.NICKEL);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.HALF);
        assertEquals(3.15, vendingMachine.countTotalValueOfInsertedCoins());
    }

    @Test
    void shouldReturnTrue_whenCanAffordBuyAllOrderedProducts(){
        vendingMachine.getStock().populateStock();
        vendingMachine.insertCoin(Coin.DOLLAR);
        vendingMachine.insertCoin(Coin.DOLLAR);
        vendingMachine.insertCoin(Coin.DIME);
        vendingMachine.insertCoin(Coin.NICKEL);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.HALF);
        vendingMachine.orderProduct(Product.COLA);
        vendingMachine.orderProduct(Product.COLA);
        vendingMachine.orderProduct(Product.CANDY);
        vendingMachine.orderProduct(Product.CHIPS);
        assertTrue(vendingMachine.checkIfCanAffordForPurchase());
    }

    @Test
    void shouldReturnTrue_whenThereIsChangeToGive(){
        vendingMachine.getStock().populateStock();
        vendingMachine.insertCoin(Coin.DOLLAR);
        vendingMachine.orderProduct(Product.CHIPS);
        assertTrue(vendingMachine.checkIfNeedToGiveTheChange());
    }
    @Test
    void shouldReturnFalse_whenThereIsNoChangeToGive(){
        vendingMachine.getStock().populateStock();
        vendingMachine.insertCoin(Coin.DOLLAR);
        vendingMachine.orderProduct(Product.COLA);
        assertFalse(vendingMachine.checkIfNeedToGiveTheChange());
    }
}