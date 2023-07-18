package com.example.booksystem.repositories;

import com.example.booksystem.models.Author;
import com.example.booksystem.models.Book;
import com.example.booksystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}

