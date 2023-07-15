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
@Table(name = "continents")
@Entity
public class Continent extends BaseEntity{

    @Column
    private String name;

    @OneToMany
    private Set<Country> countries;
}
