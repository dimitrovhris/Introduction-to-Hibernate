package bg.softuni.productsshop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final String MAIN_PATH_STRING = "src/main/resources/dbContent/";
    public static final Path USERS_JSON_PATH = Path.of(MAIN_PATH_STRING,"users.json");
    public static final Path CATEGORIES_JSON_PATH = Path.of(MAIN_PATH_STRING,"categories.json");;
    public static final Path PRODUCTS_JSON_PATH = Path.of(MAIN_PATH_STRING,"products.json");;
}
