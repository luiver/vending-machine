package com.codecool;

import com.codecool.model.UserWallet;
import com.codecool.model.Wallet;

public class Main {

    public static void main(String[] args) {
        System.out.println("Vending Machine v1");
        Wallet userWallet = new UserWallet();
        userWallet.populateWallet();
        userWallet.printWallet();
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.init();
    }
}
