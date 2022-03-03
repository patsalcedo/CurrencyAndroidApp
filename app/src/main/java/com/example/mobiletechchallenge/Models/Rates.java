package com.example.mobiletechchallenge.Models;

import java.util.Map;

public class Rates {
    Map<String, Double> currencies = null;

    public Map<String, Double> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Double> currencies) {
        this.currencies = currencies;
    }
}
