package com.codecool.service;

import com.codecool.VendingMachine;
import com.codecool.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private VendingMachine vendingMachine;

    @BeforeEach
    void beforeEach() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void shouldAddNewProductIntoOrderedProducts_whenProductOrdered() {
        vendingMachine.getStock().addToStock(Product.COLA, 1);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        assertEquals(1, vendingMachine.getOrderedProducts().get(Product.COLA));
    }

    @Test
    void shouldUpdateProductAmount_whenSecondSameProductOrdered() {
        vendingMachine.getStock().addToStock(Product.COLA, 5);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        assertEquals(2, vendingMachine.getOrderedProducts().get(Product.COLA));
    }

    @Test
    void shouldNotAddMoreProductToOrderedProducts_whenProductOutOfStock() {
        vendingMachine.getStock().addToStock(Product.COLA, 1);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        assertEquals(1, vendingMachine.getOrderedProducts().get(Product.COLA));
    }

    @Test
    void shouldReturnTotalPriceOfOrderedProducts_whenProductsOrdered() {
        vendingMachine.getStock().populateStock();
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        vendingMachine.getOrderService().orderProduct(Product.COLA);
        vendingMachine.getOrderService().orderProduct(Product.CANDY);
        vendingMachine.getOrderService().orderProduct(Product.CHIPS);
        assertEquals(3.15, vendingMachine.getOrderService().getChangeService().countTotalPriceOfOrderedProducts());
    }
}