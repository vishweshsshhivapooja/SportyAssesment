package org.sporty.service;

import jakarta.annotation.PostConstruct;
import org.sporty.model.Book;
import org.sporty.model.BookType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    // In-memory store for books: key is book id.
    private Map<Long, Book> bookInventory = new HashMap<>();

    @PostConstruct
    public void init() {
        bookInventory.put(1L, new Book(1L, "The New World", "Author A", 500, BookType.NEW_RELEASE));
        bookInventory.put(2L, new Book(2L, "Regular Life", "Author B", 300, BookType.REGULAR));
        bookInventory.put(3L, new Book(3L, "Old Tales", "Author C", 200, BookType.OLD_EDITION));
        bookInventory.put(4L, new Book(4L, "Modern Times", "Author D", 400, BookType.REGULAR));
        bookInventory.put(5L, new Book(5L, "Ancient Myths", "Author E", 250, BookType.OLD_EDITION));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookInventory.values());
    }

    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookInventory.get(id));
    }

    // Additional methods (add, update, delete) can be implemented as needed.
}
