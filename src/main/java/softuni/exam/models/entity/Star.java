package softuni.exam.models.entity;
import lombok.Getter;
import lombok.Setter;

import softuni.exam.enums.StarType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Table(name = "stars")
@Entity
public class Star extends BaseEntity {

    @Column
    private String name;

    @Column(name = "light_years")
    private Double lightYears;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private StarType starType;

    @OneToMany(mappedBy = "observingStar", fetch = FetchType.EAGER)
    private Set<Astronomer> observers;

    @ManyToOne
    private Constellation constellation;

}
