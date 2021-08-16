package com.javatab.examples;

import rx.Observable;

import java.util.List;

/**
 * Created by nasir on 10/1/16.
 */
public class StockerServer {
    public static Observable<StockInfo> getFeed(List<String> symbols) {
        return Observable.create(subscriber -> {
           while (true) {
               for (String symbol : symbols) {
                   StockInfo info = StockInfo.fetch(symbol);
                   subscriber.onNext(info);
               }
               sleep(1000);
           }
        });
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {}
    }
}
