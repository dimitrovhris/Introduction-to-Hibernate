package com.example.booksystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "authors")
@Entity
public class Author extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;

    @Column(name= "last_name", nullable = false)
    private String lastName;

    @OneToMany
    private Set<Book> books;
}
