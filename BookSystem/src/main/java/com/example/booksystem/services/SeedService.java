package com.example.booksystem.services;

import java.io.IOException;

public interface SeedService {
    void seedAuthors() throws IOException;

    void seedBooks() throws IOException;

    void seedCategories() throws IOException;

    default void seedAllData() throws IOException {
        seedAuthors();
        seedBooks();
        seedCategories();
    }
}
