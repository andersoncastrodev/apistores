package br.com.asoft.apistores.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class CorsConfig {

    // Method 1: Using WebMvcConfigurer (recommended approach)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization", "Set-Cookie") // Adicione Set-Cookie
                        .allowCredentials(true) // Essencial para cookies
                        .maxAge(3600L); // 1 hour cache for preflight requests
            }
        };
    }

    // Method 2: Using CorsFilter (this is a more comprehensive approach)
    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("Set-Cookie"));
        config.setAllowCredentials(true); // Isso é CRUCIAL
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//
//        // Allow all origins for now - will be restricted to your domain in production
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//        config.setAllowedHeaders(Arrays.asList(
//                "Origin", "Content-Type", "Accept",
//                "Authorization", "X-Requested-With",
//                "Cache-Control", "Cookie"  // Adicione os headers específicos que está usando
//        ));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        config.setAllowCredentials(true);
//        config.setExposedHeaders(Arrays.asList("Authorization", "Set-Cookie")); // Adicione Set-Cookie se necessário
//        config.setMaxAge(3600L); // 1 hour cache for preflight requests
//
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
    }
}
