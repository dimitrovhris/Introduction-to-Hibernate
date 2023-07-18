package com.example.booksystem.services;

import com.example.booksystem.models.Book;

import java.util.Set;

public interface BookService {
    boolean isDataSeeded();

    void seedBooks(Set<Book> authors);
}
