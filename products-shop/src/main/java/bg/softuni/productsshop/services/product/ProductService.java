package bg.softuni.productsshop.services.product;


import java.io.FileNotFoundException;

public interface ProductService {
    void seedProducts() throws FileNotFoundException;
}
