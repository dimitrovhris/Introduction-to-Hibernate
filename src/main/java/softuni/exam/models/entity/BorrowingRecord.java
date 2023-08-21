package softuni.exam.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "borrowing_records")
@Entity
public class BorrowingRecord extends BaseEntity{

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column
    private String remarks;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Book book;

    @ManyToOne(cascade = CascadeType.MERGE)
    private LibraryMember member;
}
