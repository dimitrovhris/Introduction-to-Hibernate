package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity{

    @Column
    private Date date;

    @Column
    private String comments;

    @ManyToOne
    private Patient patient;

}
