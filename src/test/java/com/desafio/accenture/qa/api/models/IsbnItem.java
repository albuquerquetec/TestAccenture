package com.desafio.accenture.qa.api.models;

public class IsbnItem {

    private String isbn;

    public IsbnItem() {
    }

    public IsbnItem(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
