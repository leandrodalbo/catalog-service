package com.boot.demo.catalog.service;

import com.boot.demo.catalog.domain.Book;
import com.boot.demo.catalog.exceptions.AlreadyExistException;
import com.boot.demo.catalog.exceptions.NotFoundException;
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
                .orElseThrow(NotFoundException::new);
    }

    public Book newBook(Book book) {
        if (repository.existsByIsbn(book.isbn())) {
            throw new AlreadyExistException();
        }

        return repository.save(book);
    }

    public void removeBook(String isbn) {
        if (repository.existsByIsbn(isbn)) {
            repository.deleteByIsbn(isbn);
        } else {
            throw new NotFoundException();
        }
    }


    public Book updateBook(Book book) {
        var existing = repository.existsByIsbn(book.isbn());

        if (existing) {
            return repository.save(new Book(
                    book.isbn(),
                    book.title(),
                    book.author(),
                    book.price()
            ));

        }

        throw new NotFoundException();
    }
}
