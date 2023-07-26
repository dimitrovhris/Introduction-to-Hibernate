package bg.softuni.productsshop.domain.models;

import bg.softuni.productsshop.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSeedDto {
    private String name;
    private BigDecimal price;
    private Long buyerId;
    private Long sellerId;
    private User buyer;
    private User seller;
}
