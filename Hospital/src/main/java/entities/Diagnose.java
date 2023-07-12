package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "diagnoses")
public class Diagnose extends BaseEntity{

    @Column
    private String name;

    @Column
    private String comments;

    @ManyToOne
    private Patient patient;
}
