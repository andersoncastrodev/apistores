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
                        .title("Public-Notices")
                        .description("Public-Notices Editais - Unipex")
                        .contact(new Contact().name("unichristus"))
                        .version("1.0.0") );

    }

}
