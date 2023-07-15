package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "players")
@Entity
public class Player extends BaseEntity{

    @Column
    private String name;

    @Column
    private Integer squadNumber;

    @ManyToOne
    private Team team;

    @OneToOne
    private Position position;

    @Column(name = "is_currently_injured")
    private Boolean isCurrentlyInjured;


}
