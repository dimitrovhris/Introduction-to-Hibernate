package softuni.exam.models.entity;

import lombok.Getter;
import lombok.Setter;
import softuni.exam.enums.Genre;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "books")
@Entity
public class Book extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    private Double rating;

    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    private Set<BorrowingRecord> borrowingRecords;

}
