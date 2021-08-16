package com.javatab.examples;

import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nasir on 10/1/16.
 */
@Service
public class Example2 {

    public static void main(String[] args) {
        //Integer[] numbers = {0, 1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        Observable numberObservable = Observable.from(numbers);

        numberObservable.subscribe(
                //onNext()
                (fromSubscribeNumbers) -> System.out.println("I have subscribed to : " + fromSubscribeNumbers),
                //onError()
                (error) -> System.out.println("I have encountered error " + ((Throwable)error).getMessage()),
                //onCompleted()
                () -> System.out.println("This observable is finished")
        );
    }
}
