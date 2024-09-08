package uz.aziz.pricelistms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Price list Microservice API")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Aziz Ortukov")
                                .email("azizortukov818@gmail.com")
                                .url("https://t.me/aziz_ortukov"))
                        .description("Documentation of price list Microservice API")
                        .termsOfService("https://swagger.io/terms/"));
    }
}
