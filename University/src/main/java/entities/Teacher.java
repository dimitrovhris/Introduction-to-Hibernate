package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table
@Entity
public class Teacher extends BasePerson{

    @Column(name = "email")
    private String email;

    @Column(name = "salary_per_hour")
    private Double salaryPerHour;

}
