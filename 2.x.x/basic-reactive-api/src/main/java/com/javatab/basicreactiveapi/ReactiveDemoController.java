package com.javatab.basicreactiveapi;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveDemoController {

    private Flux<Book> books;

    public ReactiveDemoController() {
        books = Flux.just(
                new Book("Cloud Native Java"),
                new Book("Effective Java"),
                new Book("Clean Code"),
                new Book("Designing Data Intensive Application")
        );
    }

    @GetMapping
    Flux<Book> getBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    Flux<Book> getBookById(@PathVariable String id) {
        return books.filter(book -> book.getId().equals(id)).flatMap(Mono::just).switchIfEmpty(Mono.empty());
    }

    @PostMapping
    Mono<Book> postBook(@RequestBody Book book) {
        books = Flux.concat(books, Flux.just(book));
        return Mono.just(book);
    }

    @PutMapping("/books/{id}")
    Mono<Book> putBook(@PathVariable String id, @RequestBody Book book) {
        return books.filter(aBook -> aBook.getId().equals(id)).last().flatMap(filteredBook -> {
            filteredBook.setName(book.getName());
            return Mono.just(filteredBook);
        });
    }
}
