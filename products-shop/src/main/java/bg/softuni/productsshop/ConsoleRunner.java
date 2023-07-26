package bg.softuni.productsshop;

import bg.softuni.productsshop.services.product.ProductService;
import bg.softuni.productsshop.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    @Autowired
    public ConsoleRunner(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        productService.seedProducts();
    }
}
