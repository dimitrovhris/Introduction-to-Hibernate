package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Table(name = "teams")
@Entity
public class Team extends BaseEntity{

    @Column
    private String name;

    @Column
    private String logo;

    @Column(length = 3)
    private String initial;

    @Column(name = "primary_kit_color")
    @OneToOne
    private Color primaryKitColor;


    @Column(name = "secondary_kit_color")
    @OneToOne
    private Color secondaryKitColor;

    @OneToOne
    private Town town;

    @Column
    private BigDecimal budget;

    @OneToMany
    private Set<Player> players;

}
