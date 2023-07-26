package bg.softuni.productsshop.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
@Table(name = "categories")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Column
    @Length(min = 3,max = 15)
    private String name;

    @OneToMany
    private Set<Product> products;
}
