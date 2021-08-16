package com.javatab.examples;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nasir on 10/1/16.
 * Adapted from Venkat Subramaniam webinar
 */
public class Example3 {
    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("AMZN", "GOOG", "ORCL");

        Observable<StockInfo> feed = StockerServer.getFeed(symbols);

        feed.subscribe(System.out::println);

        System.out.println("END");
    }
}
