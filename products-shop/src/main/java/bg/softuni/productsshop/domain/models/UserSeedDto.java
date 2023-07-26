package bg.softuni.productsshop.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserSeedDto implements Serializable {
    private String firstName;

    private String lastName;

    private int age;

}
