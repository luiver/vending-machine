package com.codecool.service;

import com.codecool.UI.View;
import com.codecool.model.Coin;
import com.codecool.model.Product;
import com.codecool.model.Stock;
import com.codecool.model.Wallet;

import java.util.Map;

public class OrderService {
    private Stock stock;
    private Map<Product, Integer> orderedProducts;
    private ChangeService changeService;
    private CancelService cancelService;
    private Map<Coin, Integer> insertedCoins;
    private View view;

    public OrderService(Stock stock, Map<Coin, Integer> insertedCoins, Map<Product, Integer> orderedProducts, Wallet machineWallet, CancelService cancelService ) {
        this.stock = stock;
        this.insertedCoins = insertedCoins;
        this.orderedProducts = orderedProducts;
        this.changeService = new ChangeService(insertedCoins, orderedProducts, machineWallet);
        this.cancelService = cancelService;
        this.view = new View();
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

    public void makeOrder(Product product) {
        if (orderProduct(product)){
            if(changeService.checkIfCanAffordForPurchase()){
                if(changeService.checkIfNeedToGiveTheChange()){
                    view.print("\nCHANGE: " + changeService.countChangeToGive() + "$");
                    view.printMap(changeService.giveChange(changeService.countChangeToGive()));
                }
                view.printWithSleep("THANK YOU",3000);
                cancelService.returnAllInsertedCoins();
                //stock.printStock();
                return;
            }
            view.printWithSleep("PRODUCT: "+ product +" PRICE: " + product.getPrice() + "$\n",3000);
            cancelService.returnOrderedProductsToStock();
            cancelService.clearProductsFromMap();
            //stock.printStock();
        } else {
            view.printWithSleep("PRODUCT: "+ product +" SOLD OUT",3000);
        }
    }
}
