package com.codecool.service;

import com.codecool.model.Coin;
import com.codecool.model.Product;
import com.codecool.model.Stock;
import com.codecool.model.Wallet;

import java.util.Map;

public class OrderService {
    private Stock stock;
    private Map<Product, Integer> orderedProducts;
    private ChangeService changeService;

    public OrderService(Stock stock, Map<Coin, Integer> insertedCoins, Map<Product, Integer> orderedProducts, Wallet machineWallet ) {
        this.stock = stock;
        this.orderedProducts = orderedProducts;
        this.changeService = new ChangeService(insertedCoins, orderedProducts, machineWallet);
    }

    public boolean orderProduct(Product product){
        if (stock.checkIfProductSoldOut(product)) {
            return false;
        }
        updateOrderedProducts(product);
        return true;
    }


    private void updateOrderedProducts(Product product) {
        int quantity = 1;
        if (orderedProducts.get(product) != null) {
            orderedProducts.computeIfPresent(product, (k, v) -> v + quantity);
        } else {
            orderedProducts.put(product, quantity);
        }
        stock.removeFromStock(product, quantity);
    }

    public ChangeService getChangeService() {
        return changeService;
    }
}
