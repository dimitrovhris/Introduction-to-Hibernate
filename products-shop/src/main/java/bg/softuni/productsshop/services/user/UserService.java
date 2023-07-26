package bg.softuni.productsshop.services.user;

import bg.softuni.productsshop.domain.entities.User;

import java.io.FileNotFoundException;
import java.util.Optional;

public interface UserService {
    void seedUsers() throws FileNotFoundException;

    User getRandomPerson();
}
