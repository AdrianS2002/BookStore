package model;

// Java Bean -

// POJO - Plain Old Java Object


import java.time.LocalDate;

public class Book{

    private Long id;

    private String author;

    private String title;

    private LocalDate publishedDate;

    private int quantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getQuantity() {
    	return quantity;
    }

    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    }

    @Override
    public String toString(){
        return String.format("Id: %d | Title: %s | Author: %s | Date: %s | Quantity: %d", this.id, this.title, this.author, this.publishedDate, this.quantity);
    }
}
