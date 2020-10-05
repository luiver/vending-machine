package com.codecool.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    private Stock stock;

    @BeforeEach
    void beforeEach(){
        stock = new Stock();
        stock.populateStock();
    }

    @Test
    void shouldReturnTrue_whenSufficientAmountOFProductsOnStock() {
        stock.getProductsOnStock().put(Product.COLA, 1);
        assertTrue(stock.checkIfSufficientProductsOnStock(Product.COLA, 1));
    }

    @Test
    void shouldReturnFalse_whenZeroProductsOnStock() {
        stock.getProductsOnStock().put(Product.COLA, 0);
        assertFalse(stock.checkIfSufficientProductsOnStock(Product.COLA, 1));
    }

    @Test
    void shouldReturnFalse_whenInsufficientAmountOFProductsOnStock() {
        stock.getProductsOnStock().put(Product.COLA, 10);
        assertFalse(stock.checkIfSufficientProductsOnStock(Product.COLA, 11));
    }

    @Test
    void shouldReturnProperNumberOfItemsOnStock_whenRemoveSomeAmountItemsFromIt() {
        stock.removeFromStock(Product.COLA,9);
        assertEquals(stock.getProductsOnStock().get(Product.COLA),990);
    }

    @Test
    void shouldReturnFalse_whenRequestedProductQuantityExtendsItemsOnStock() {
        assertFalse(stock.removeFromStock(Product.COLA,9999));
    }

    @Test
    void shouldReturnTrue_whenSuccessfullyRemoveItemsFromStock() {
        assertTrue(stock.removeFromStock(Product.COLA,999));
    }


}