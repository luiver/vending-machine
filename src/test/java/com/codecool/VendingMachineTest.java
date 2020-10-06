package com.codecool;

import com.codecool.model.Coin;
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
    void shouldReturnNull_whenInvalidCoinInserted(){
        assertNull(vendingMachine.getInsertedCoins().get(Coin.PENNY));
    }
}