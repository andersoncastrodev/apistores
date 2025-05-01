package br.com.asoft.apistores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Sales Store")
                        .description("Api Sales Store - ASoft")
                        .contact(new Contact().name("asoftSistemas"))
                        .version("1.0.0") );

    }

}
