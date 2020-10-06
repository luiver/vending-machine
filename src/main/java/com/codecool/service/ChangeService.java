package com.codecool.service;

import com.codecool.model.Coin;
import com.codecool.model.Product;
import com.codecool.model.Wallet;

import java.util.*;
import java.util.stream.Collectors;

public class ChangeService {
    private final Map<Coin, Integer> insertedCoins;
    private final Map<Product, Integer> orderedProducts;
    private Wallet machineWallet;

    public ChangeService(Map<Coin, Integer> insertedCoins, Map<Product, Integer> orderedProducts, Wallet machineWallet) {
        this.insertedCoins = insertedCoins;
        this.orderedProducts = orderedProducts;
        this.machineWallet = machineWallet;
    }

    public boolean checkIfCanAffordForPurchase() {
        return (countChangeToGive()) >= 0;
    }

    public boolean checkIfNeedToGiveTheChange() {
        return (countChangeToGive()) > 0;
    }

    public double countChangeToGive() {
        return Math.round((countTotalValueOfInsertedCoins() - countTotalPriceOfOrderedProducts()) * 100) / 100.00;
    }

    public Map<Coin, Integer> giveChange(double changeToGive) {
        List<Map<Coin, Integer>> coins = new ArrayList<>();
        int total = (int) (changeToGive * 100);
        int combinations = 0;
        //System.out.printf("%s\t%s\t%s\t%s\t%s\n","o", "h", "q", "d", "n");
        for (int o = 0; o <= total / 100; o++) {
            int total_less_o = total - o * 100;
            for (int h = 0; h <= total_less_o / 50; h++) {
                int total_less_o_h = total_less_o - h * 50;
                for (int q = 0; q <= total_less_o_h / 25; q++) {
                    int total_less_o_h_q = total_less_o_h - q * 25;
                    for (int d = 0; d <= total_less_o_h_q / 10; d++) {
                        int total_less_o_h_q_d = total_less_o_h_q - d * 10;
                        for (int n = 0; n <= total_less_o_h_q_d / 5; n++) {
                            if ((o*100 + h*50 + q*25 + d*10 + n*5) == total) {
                                //System.out.printf("%d\t%d\t%d\t%d\t%d\n", o, h, q, d, n);
                                combinations++;
                                if(checkIfMachineWalletContainsProperAmountOfCoinsToGiveChange(o, h, q, d, n)){
                                    coins = addPotentialCombinationOfCoins(coins, o, h, q, d, n);
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.printf("%d combinations\n", combinations);
        return selectMinimumCoinsAmountToGiveAsChange(coins);
    }

    private Map<Coin, Integer> selectMinimumCoinsAmountToGiveAsChange(List<Map<Coin, Integer>> coins) {
        List<Map<Coin, Integer>> list = coins.stream().sorted(Comparator.comparing(map -> map.values().stream()
                .mapToInt(Number::intValue).sum())).collect(Collectors.toList());
        //System.out.println(list.get(0));
        //System.out.println(list.get(list.size()-1));
        return list.get(0);
    }

    private List<Map<Coin, Integer>> addPotentialCombinationOfCoins(List<Map<Coin, Integer>> coins, int o, int h, int q, int d, int n) {
        Map<Coin, Integer> coinsCombination = new HashMap<>();
        coinsCombination.put(Coin.DOLLAR, o);
        coinsCombination.put(Coin.HALF, h);
        coinsCombination.put(Coin.QUARTER, q);
        coinsCombination.put(Coin.DIME, d);
        coinsCombination.put(Coin.NICKEL, n);
        coins.add(coinsCombination);
        return coins;
    }

    private boolean checkIfMachineWalletContainsProperAmountOfCoinsToGiveChange(int o, int h, int q, int d, int n) {
        boolean isEnoughDollars = machineWallet.checkIfSufficientCoinsInWallet(Coin.DOLLAR, o);
        boolean isEnoughHalves = machineWallet.checkIfSufficientCoinsInWallet(Coin.HALF, h);
        boolean isEnoughQuarters = machineWallet.checkIfSufficientCoinsInWallet(Coin.QUARTER, q);
        boolean isEnoughDimes = machineWallet.checkIfSufficientCoinsInWallet(Coin.DIME, d);
        boolean isEnoughNickels = machineWallet.checkIfSufficientCoinsInWallet(Coin.NICKEL, n);
        return (isEnoughDollars && isEnoughHalves && isEnoughQuarters && isEnoughDimes && isEnoughNickels);
    }

    public double countTotalValueOfInsertedCoins() {
        double total = 0.00;
        for (Map.Entry<Coin, Integer> entry : insertedCoins.entrySet()) {
            double coinValue = entry.getKey().getValue();
            int numberOfCoins = entry.getValue();
            total += coinValue * numberOfCoins;
        }
        return total;
    }

    public double countTotalPriceOfOrderedProducts() {
        double total = 0.00;
        for (Map.Entry<Product, Integer> entry : orderedProducts.entrySet()) {
            double productPrice = entry.getKey().getPrice();
            int numberOfProducts = entry.getValue();
            total += productPrice * numberOfProducts;
        }
        return total;
    }

    public void exactChangeOnly() {

    }
}
