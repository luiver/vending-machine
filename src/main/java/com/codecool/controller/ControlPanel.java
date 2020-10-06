package com.codecool.controller;

import com.codecool.UI.IO;
import com.codecool.UI.View;
import com.codecool.model.Coin;
import com.codecool.model.Product;
import com.codecool.service.CancelService;
import com.codecool.service.InsertService;
import com.codecool.service.OrderService;

import java.util.HashMap;
import java.util.Map;

public class ControlPanel {
    private boolean isRunning;
    private Map<Integer, Runnable> mainMenu;
    private InsertService insertService;
    private OrderService orderService;
    private CancelService cancelService;
    private View view;
    private IO io;
    private String displayInfo;

    public ControlPanel(InsertService insertService, OrderService orderService, CancelService cancelService) {
        this.isRunning = true;
        this.insertService = insertService;
        this.orderService  = orderService;
        this.cancelService = cancelService;
        this.view = new View();
        this.io = new IO();
    }

    public void runMenu() {
        initializeMainMenu();
        while(isRunning) {
            displayInfo = getWhatHaveToBeDisplayed();
            view.print(gatherContextMenu(displayInfo));
            int userChoice = io.gatherIntInput("\nEnter a number: ", 0, 9);
            mainMenu.get(userChoice).run();
        }
    }

    private String getWhatHaveToBeDisplayed() {
        if (orderService.getChangeService().countTotalValueOfInsertedCoins() == 0){
            return orderService.getChangeService().checkIfExactChangeIsRequired();
        }
        return "BALANCE: " + orderService.getChangeService().countTotalValueOfInsertedCoins() + "$";
    }

    public String[] gatherContextMenu(String displayInfo) {
        return new String[]{"\n" + displayInfo +"\n"
                + "(1) Insert Dollar\n"
                + "(2) Insert Half\n"
                + "(3) Insert Quarter\n"
                + "(4) Insert Dime\n"
                + "(5) Insert Nickel\n"
                + "(6) Order Cola\n"
                + "(7) Order Chips\n"
                + "(8) Order Candy\n"
                + "(9) Return Coins\n"
                + "(0) Exit"};
    }

    public void initializeMainMenu() {
        mainMenu = new HashMap<>();
        mainMenu.put(1, () -> insertService.insertCoin(Coin.DOLLAR));
        mainMenu.put(2, () -> insertService.insertCoin(Coin.HALF));
        mainMenu.put(3, () -> insertService.insertCoin(Coin.QUARTER));
        mainMenu.put(4, () -> insertService.insertCoin(Coin.DIME));
        mainMenu.put(5, () -> insertService.insertCoin(Coin.NICKEL));
        mainMenu.put(6, () -> orderService.makeOrder(Product.COLA));
        mainMenu.put(7, () -> orderService.makeOrder(Product.CHIPS));
        mainMenu.put(8, () -> orderService.makeOrder(Product.CANDY));
        mainMenu.put(9, () -> cancelService.cancelTransaction());
        mainMenu.put(0, this::exit);
    }

    private void exit() {
        isRunning = false;
        System.out.println("\nBye bye\n");
    }
}
