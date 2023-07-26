package bg.softuni.productsshop.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.FetchMode;
import org.hibernate.engine.profile.Fetch;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Table(name = "products")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{

    @Column(nullable = false)
    @Length(min = 3)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "buyer_id", insertable=false, updatable=false)
    private Long buyerId;

    @Column(name = "seller_id", insertable=false, updatable=false)
    private Long sellerId;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private User seller;

    @OneToMany
    private Set<Category> categories;
}
