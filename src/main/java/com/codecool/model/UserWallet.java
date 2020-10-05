package com.codecool.model;

public class UserWallet extends Wallet{

    public UserWallet() {
        super();
    }

    @Override
    public void populateWallet() {
        coins.put(Coin.PENNY, 100);
        coins.put(Coin.NICKEL, 100);
        coins.put(Coin.DIME, 50);
        coins.put(Coin.QUARTER, 10);
        coins.put(Coin.HALF, 10);
        coins.put(Coin.DOLLAR, 10);
    }

    @Override
    public void printWallet(){
        System.out.println("\nYour Wallet:");
        System.out.println("Coin: \t\tAmount:");
        coins.forEach((k, v) -> System.out.println(k + "\t\t" + v));
    }
}
