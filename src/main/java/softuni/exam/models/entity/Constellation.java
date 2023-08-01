package softuni.exam.models.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "constellations")
@Entity
public class Constellation extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "constellation", fetch = FetchType.EAGER)
    private Set<Star> stars;
}
