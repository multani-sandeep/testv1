package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class HelloShopConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(HelloShopConfiguration.class, args);
    }

    @Bean
    public ShopFacade shopFacade() {
        return new ShopFacade();
    }
    
    @Bean
    public MapService mapService() {
        return new MapService();
    }
}