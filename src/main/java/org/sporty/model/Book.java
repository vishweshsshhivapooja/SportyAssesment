package org.sporty.model;

public class Book {
    private Long id;
    private String title;
    private String author;
    private double basePrice;
    private BookType bookType;

    public Book() {}

    public Book(Long id, String title, String author, double basePrice, BookType bookType) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.basePrice = basePrice;
        this.bookType = bookType;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public BookType getBookType() { return bookType; }
    public void setBookType(BookType bookType) { this.bookType = bookType; }
}
