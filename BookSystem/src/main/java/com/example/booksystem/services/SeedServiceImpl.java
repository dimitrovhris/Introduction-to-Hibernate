package com.example.booksystem.services;

import com.example.booksystem.models.Author;
import com.example.booksystem.models.Book;
import com.example.booksystem.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.booksystem.constants.FilePath.*;


@Service
public class SeedServiceImpl implements SeedService{

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public SeedServiceImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(this.authorService.isDataSeeded()) return;

        this.authorService.seedAuthors(
        Files.readAllLines(Path.of(RESOURCE_URL + AUTHOR_FILE_NAME))
                .stream()
                .map(firstAndLastName -> Author.builder()
                                .firstName(firstAndLastName.split(" ")[0])
                                .lastName(firstAndLastName.split(" ")[1])
                                .build())
                .collect(Collectors.toSet()));
    }

    @Override
    public void seedBooks() throws IOException {
        if(this.bookService.isDataSeeded()) return;

        this.bookService.seedBooks(
                Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                        .stream()
                        .map(row -> {
                                String[] args = row.split("\\s+");
                                Author randomAuthor = this.authorService.getRandomAuthor();
                            Category randomCategories = this.categoryService.getRandomCategory();
                            new Book();
                        })
                        .collect(Collectors.toSet());
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryService.isDataSeeded()) return;

        this.categoryService.seedCategories(
        Files.readAllLines(Path.of(RESOURCE_URL + CATEGORY_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .collect(Collectors.toSet()));
    }

}
