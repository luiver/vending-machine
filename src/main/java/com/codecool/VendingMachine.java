package com.codecool;

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

    public VendingMachine() {
        stock = new Stock();
        machineWallet = new MachineWallet();
        insertedCoins = new HashMap<>();
        orderedProducts = new HashMap<>();
        insertService = new InsertService(insertedCoins);
        orderService = new OrderService(stock, insertedCoins, orderedProducts);
        cancelService = new CancelService(stock, orderedProducts);
    }

    public void init(){
        stock.populateStock();
        machineWallet.populateWallet();
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
