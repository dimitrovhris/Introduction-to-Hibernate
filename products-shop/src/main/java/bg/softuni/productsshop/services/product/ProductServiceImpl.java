package bg.softuni.productsshop.services.product;

import bg.softuni.productsshop.domain.entities.Product;
import bg.softuni.productsshop.domain.entities.User;
import bg.softuni.productsshop.domain.models.ProductSeedDto;
import bg.softuni.productsshop.repositories.ProductRepository;
import bg.softuni.productsshop.repositories.UserRepository;
import bg.softuni.productsshop.services.user.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static bg.softuni.productsshop.constants.Paths.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserService userService, Gson gson) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.gson = gson;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        if (this.productRepository.count() != 0) return;

        ProductSeedDto[] productSeedDtos = gson.fromJson(new FileReader(PRODUCTS_JSON_PATH.toFile()), ProductSeedDto[].class);
        for (ProductSeedDto productSeedDto : productSeedDtos) {
            User buyer = userService.getRandomPerson();
            User seller = userService.getRandomPerson();
            Long buyerId = buyer.getId();
            Long sellerId = seller.getId();
            if(buyer == seller) continue;
            productSeedDto.setBuyerId(buyerId);
            productSeedDto.setSellerId(sellerId);
            productSeedDto.setBuyer(buyer);
            productSeedDto.setSeller(seller);
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            Product productToSeed = modelMapper.map(productSeedDto, Product.class);
            productRepository.saveAndFlush(productToSeed);

        }
    }
}
