package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Table(name = "countries")
@Entity
public class Country extends BaseEntity{
    @Column
    private String name;

    @OneToMany
    private Set<Town> towns;

    @OneToMany
    private Set<Continent> continents;
}
