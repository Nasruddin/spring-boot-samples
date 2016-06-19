package com.javatab.examples;

/**
 * Created by nasir on 10/1/16.
 */
public class StockInfo {

    public final String ticker;
    public final Double value;


    public StockInfo(String theTicker, Double theValue) {
        ticker = theTicker;
        value = theValue;
    }

    public static StockInfo fetch(String ticker) {
        if (Math.random() > 0.95) throw new RuntimeException("Oops, ran into trouble");

        return new StockInfo(ticker, YahooService.getPrice(ticker, false));
    }

    @Override
    public String toString() {
        return String.format("%s : %t", ticker, value);
    }
}
