# rxjava-springboot-starter
A demo implementation of RxJava

Documentation is in process !
ReactiveX is a library for composing asynchronous and event-based programs by using observable sequences.

It extends the observer pattern to support sequences of data and/or events and adds operators that allow you to compose sequences together declaratively while abstracting away concerns about things like low-level threading, synchronization, thread-safety, concurrent data structures, and non-blocking I/O.

RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.

It extends the observer pattern to support sequences of data/events and adds operators that allow you to compose sequences together declaratively while abstracting away concerns about things like low-level threading, synchronization, thread-safety and concurrent data structures.

Zero Dependencies
< 800KB Jar
Java 6+ & Android 2.3+
Java 8 lambda support
Polyglot (Scala, Groovy, Clojure and Kotlin)
Non-opinionated about source of concurrency (threads, pools, event loops, fibers, actors, etc)
Async or synchronous execution
Virtual time and schedulers for parameterized concurrency

Event       |       Iterable(Pull)         |      Observable(Push)
------------|------------------------------|-----------------------
Retrieve data   |  T next()        |             onNext(T)
discover error  |   throws Exception        |     onError(Exception)
Complete       |    returns                |      onCompleted()

Detailed explanation : https://javatab.wordpress.com/2016/01/10/reactive-extension-rxjava/
