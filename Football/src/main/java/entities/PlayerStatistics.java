package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "player_statistics")
@Entity
public class PlayerStatistics extends BaseEntity{
    @Column
    private String game;


}
