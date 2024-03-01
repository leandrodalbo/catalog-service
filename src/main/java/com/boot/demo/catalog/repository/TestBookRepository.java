package com.boot.demo.catalog.repository;

import com.boot.demo.catalog.domain.Book;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("dev")
public class TestBookRepository implements BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return books.containsKey(isbn);
    }

    @Override
    public Book save(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data.test"))) {
            while ((line = reader.readLine()) != null) {

                String[] keyValue = line.split(":");
                String[] bookFields = keyValue[1].split(",");

                this.books.put(keyValue[0], new Book(keyValue[0], bookFields[0], bookFields[1], bookFields[2]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
