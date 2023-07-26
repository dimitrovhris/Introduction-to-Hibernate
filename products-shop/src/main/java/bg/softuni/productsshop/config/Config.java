package bg.softuni.productsshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;

public class Config {
    @Bean
    public Gson createGson(){
        return new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }
}

