package com.codecool;

import com.codecool.controller.ControlPanel;
import com.codecool.model.*;
import com.codecool.service.CancelService;
import com.codecool.service.InsertService;
import com.codecool.service.OrderService;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Stock stock;
    private Wallet machineWallet;
    private Map<Coin, Integer> insertedCoins;
    private Map<Product, Integer> orderedProducts;
    private InsertService insertService;
    private OrderService orderService;
    private CancelService cancelService;
    private ControlPanel controlPanel;

    public VendingMachine() {
        stock = new Stock();
        machineWallet = new MachineWallet();
        insertedCoins = new HashMap<>();
        orderedProducts = new HashMap<>();
        insertService = new InsertService(insertedCoins);
        cancelService = new CancelService(stock, insertedCoins, orderedProducts);
        orderService = new OrderService(stock, insertedCoins, orderedProducts, machineWallet, cancelService);
        controlPanel = new ControlPanel(insertService, orderService, cancelService);
    }

    public void run() {
        controlPanel.runMenu();
    }

    public void init(){
        stock.populateStock();
        //stock.populateEmptyStock();
        machineWallet.populateWallet();
        //machineWallet.populateEmptyWallet();
    }

    public Wallet getMachineWallet() {
        return machineWallet;
    }

    public Stock getStock() {
        return stock;
    }

    public Map<Coin, Integer> getInsertedCoins() {
        return insertedCoins;
    }

    public Map<Product, Integer> getOrderedProducts() {
        return orderedProducts;
    }

    public InsertService getInsertService() {
        return insertService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public CancelService getCancelService() {
        return cancelService;
    }
}
