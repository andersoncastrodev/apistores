package br.com.asoft.apistores.infra;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Habilita a anotação @PreAuthorize no controllers
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()// Habilita o cors
                .and()
                .csrf( csrf -> csrf.disable()) // Habilita o csrf quando for para produção

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"auth/login").permitAll() // Habilita o login e gera o token
                        .requestMatchers(HttpMethod.GET,"auth/validate").permitAll()// Habilita a validação do token
                        .requestMatchers(HttpMethod.POST,"auth/refresh").permitAll()// Habilita a refresh do token

                        .requestMatchers(HttpMethod.GET,"/users/**").permitAll()

                        //.requestMatchers(HttpMethod.POST,"/users").permitAll()

                        //.requestMatchers(HttpMethod.GET,"/users").permitAll()
                        //.requestMatchers(HttpMethod.POST,"/user/clientes").permitAll()

                        //.requestMatchers(HttpMethod.GET, "/users/**").hasAuthority("SCOPE_ADMIN") Ex: /users/** é para todos os endpoints que começam com /users/


                        .anyRequest().authenticated()) // O resto exige autenticação

                .oauth2ResourceServer( oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    //Configuracao do cors
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:3000" , "http://127.0.0.1:3000")); // Definir quais origens podem ser usadas

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Definir quais metodos podem ser usados

        configuration.setAllowedHeaders(List.of("*"));// Definir quais headers podem ser usados

        configuration.setAllowCredentials(true); // Habilitar credenciais

        //configuration.addExposedHeader(HttpHeaders.SET_COOKIE); // Habilitar cookies.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Definir quais endpoints podem ser usados

        return source;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    //Cria uma maneira de encripar as senhas do usuario
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
