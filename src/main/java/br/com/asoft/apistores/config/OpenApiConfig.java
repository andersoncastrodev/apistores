package br.com.asoft.apistores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api Venda Store")
                        .description("Api Venda Store - ASoft")
                        .contact(new Contact().name("asoftSistemas"))
                        .version("1.0.0") );

    }

}
