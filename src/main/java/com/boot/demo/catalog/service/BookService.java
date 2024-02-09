package com.boot.demo.catalog.service;

import com.boot.demo.catalog.domain.Book;
import com.boot.demo.catalog.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Iterable<Book> allBooks() {
        return repository.findAll();
    }

    public Book bookDetails(String isbn) {
        return repository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException(""));
    }

    public Book newBook(Book book) {
        if (repository.existsByIsbn(book.isbn())) {
            throw new RuntimeException("");
        }

        return repository.save(book);
    }

    public void removeBook(String isbn) {
        repository.deleteByIsbn(isbn);
    }


    public Book updateBook(Book book) {
        return repository.findByIsbn(book.isbn())
                .map(it -> {
                    return repository.save(new Book(
                            it.isbn(),
                            book.title(),
                            book.author(),
                            book.price()
                    ));
                })
                .orElseGet(() -> newBook(book));
    }
}
