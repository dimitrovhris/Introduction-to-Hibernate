package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "medicaments")
@Entity
public class Medicament extends BaseEntity{
    @Column
    private String name;

    @ManyToOne
    private Patient patient;
}
