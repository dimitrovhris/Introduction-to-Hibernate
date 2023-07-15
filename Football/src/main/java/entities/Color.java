package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "colors")
@Entity
public class Color extends BaseEntity{

    @Column
    private String name;
}
