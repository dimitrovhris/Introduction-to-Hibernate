package com.example.booksystem.services;

import com.example.booksystem.models.Category;

import java.util.Set;

public interface CategoryService {
    boolean isDataSeeded();

    void seedCategories(Set<Category> categories);

    Category getRandomCategory();
}
