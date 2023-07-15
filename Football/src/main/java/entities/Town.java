package entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "towns")
@Entity
public class Town extends BaseEntity{
    @Column
    private String name;

    @OneToOne
    private Country country;

    @OneToMany
    private Set<Team> teams;
}
