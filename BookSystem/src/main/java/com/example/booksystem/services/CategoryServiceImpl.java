package com.example.booksystem.services;

import com.example.booksystem.models.Category;
import com.example.booksystem.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public void seedCategories(Set<Category> categories) {
        this.categoryRepository.saveAllAndFlush(categories);
    }

    @Override
    public Category getRandomCategory() {
        return null;
    }
}
