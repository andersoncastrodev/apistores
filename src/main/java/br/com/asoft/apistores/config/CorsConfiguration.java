package br.com.asoft.apistores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000");

                /*
                   Aqui devemos colocar a url do FrontEnd.
                   Ex: http://localhost:3000
                   ou  http://localhots:4200
                 */
                //.allowedOrigins("*") Vai permitir tudo.
            }
        };
    }
}
