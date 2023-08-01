package softuni.exam.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "astronomers")
@Entity
public class Astronomer extends BaseEntity {


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private Double salary;

    @Column(name = "average_observation_hours")
    private Double averageObservationHours;

    @Column
    private LocalDate birthday;

    @ManyToOne
    private Star observingStar;
}