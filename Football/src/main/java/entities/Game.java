package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "games")
@Entity
public class Game extends BaseEntity{

    @OneToOne
    private Team homeTeam;

    @OneToOne
    private Team awayTeam;

    @Column(name = "home_goals")
    private Integer homeGoals;

    @Column(name = "away_goals")
    private Integer awayGoals;
}
