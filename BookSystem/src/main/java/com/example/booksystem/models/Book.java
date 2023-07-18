package com.example.booksystem.models;

import com.example.booksystem.enums.AgeDescription;
import com.example.booksystem.enums.EditionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Table(name = "books")
@Entity
public class Book extends BaseEntity{

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "edition_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "age_restriction", nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeDescription ageDescription;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;
}
