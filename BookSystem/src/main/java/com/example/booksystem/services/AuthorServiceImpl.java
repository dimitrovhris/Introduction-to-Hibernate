package com.example.booksystem.services;

import com.example.booksystem.models.Author;
import com.example.booksystem.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public void seedAuthors(Set<Author> authors) {
        this.authorRepository.saveAllAndFlush(authors);
    }

    @Override
    public Author getRandomAuthor(){
        final long count = this.authorRepository.count();

        if(count != 0){
            long randomId = new Random().nextLong(1,count) + 1;
            this.authorRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }
        throw new RuntimeException();
    }
}
