package com.codecool.model;

public class MachineWallet extends Wallet{

    public MachineWallet() {
        super();
    }

    @Override
    public void populateWallet() {
        coins.put(Coin.NICKEL, 100);
        coins.put(Coin.DIME, 50);
        coins.put(Coin.QUARTER, 10);
        coins.put(Coin.HALF, 10);
        coins.put(Coin.DOLLAR, 10);
    }

    @Override
    public void printWallet(){
        System.out.println("\nMachine Wallet Contains:");
        System.out.println("Coin: \t\tAmount:");
        coins.forEach((k, v) -> System.out.println(k + "\t\t" + v));
    }
}
