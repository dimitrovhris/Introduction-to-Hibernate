package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BasePerson{

    @Column(name = "average_grade", precision = 3, scale = 2)
    private Double averageGrade;

    @Column(name = "attendance")
    private Integer attendance;

    @ManyToOne
    private Set<Course> courses;

}
