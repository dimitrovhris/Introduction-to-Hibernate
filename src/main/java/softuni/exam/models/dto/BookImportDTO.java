package softuni.exam.models.dto;

import lombok.Getter;
import lombok.Setter;
import softuni.exam.enums.Genre;
import softuni.exam.repository.BookRepository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class BookImportDTO {

    private String author;

    private Boolean available;

    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String title;

    private Double rating;
    

    public boolean isValid(BookRepository bookRepository) {
        if (bookRepository.findFirstByTitle(title).isEmpty()
                && title.length() >= 3 && title.length() <= 40
                && author.length() >= 3 && author.length() <= 40
                && description.length() >= 5 && rating > 0) {
            return true;
        }
        return false;
    }
}
