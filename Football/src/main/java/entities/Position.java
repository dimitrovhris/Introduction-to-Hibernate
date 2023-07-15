package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "positions")
@Entity
public class Position {

    @Id
    @Column(length = 2)
    private String id;

    @Column(name = "position_description")
    private String positionDescription;

    @OneToMany
    private Set<Player> players;
}
