package com.javatab.basic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DemoBookController {

    private final List<Book> books = new ArrayList<>();

    public DemoBookController() {
        books.addAll(List.of(
                new Book("Cloud Native Java"),
                new Book("Effective Java"),
                new Book("Clean Code"),
                new Book("Designing Data Intensive Application")
        ));
    }

    @GetMapping
    Iterable<Book> getBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    Optional<Book> getBookById(@PathVariable String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    ResponseEntity<Book> postBook(@RequestBody Book book) {
        books.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<Book> putBook(@PathVariable String id, @RequestBody Book book) {
        int bookIndex = -1;

        for (Book aBook :
                books) {
            if (aBook.getId().equals(id)) {
                bookIndex = books.indexOf(aBook);
                books.set(bookIndex, book);
            }
        }
        return (bookIndex == -1) ?
                new ResponseEntity<>(postBook(book).getBody(), HttpStatus.CREATED) :
                new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable String id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
