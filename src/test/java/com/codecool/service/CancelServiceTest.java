package com.codecool.service;

import com.codecool.VendingMachine;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CancelServiceTest {
    private VendingMachine vendingMachine;

    @BeforeEach
    void beforeEach() {
        vendingMachine = new VendingMachine();
    }

}