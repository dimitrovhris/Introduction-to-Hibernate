package com.example.booksystem.services;

import com.example.booksystem.models.Author;

import java.util.Set;

public interface AuthorService {
    boolean isDataSeeded();

    void seedAuthors(Set<Author> authors);

    Author getRandomAuthor();
}
