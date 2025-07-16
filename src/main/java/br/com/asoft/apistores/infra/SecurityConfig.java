package br.com.asoft.apistores.infra;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.filter.CorsFilter;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Habilita a anotação @PreAuthorize no controllers
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    private final CorsFilter corsFilter;

    private final CookieTokenResolver cookieTokenResolver;

    public SecurityConfig(CorsFilter corsFilter, CookieTokenResolver cookieTokenResolver) {
        this.corsFilter = corsFilter;
        this.cookieTokenResolver = cookieTokenResolver;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Habilita o csrf quando for para produção

                // Add the CORS filter before the security filters
                .addFilter(corsFilter)

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"auth/login").permitAll() // Habilita o login e gera o token
                        .requestMatchers(HttpMethod.GET,"auth/validate").permitAll()// Habilita a validação do token
                        .requestMatchers(HttpMethod.POST,"auth/refresh").permitAll()// Habilita a refresh do token
                        .requestMatchers(HttpMethod.POST,"/auth/logout").permitAll()// Habilita o logout
                        .requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow OPTIONS requests for CORS preflight

                        //.requestMatchers(HttpMethod.POST,"/users").permitAll()

                        //.requestMatchers(HttpMethod.GET,"/users").permitAll()
                        //.requestMatchers(HttpMethod.POST,"/user/clientes").permitAll()

                        //.requestMatchers(HttpMethod.GET, "/users/**").hasAuthority("SCOPE_ADMIN") Ex: /users/** é para todos os endpoints que começam com /users/


                        .anyRequest().authenticated()) // O resto exige autenticação

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder()))
                        .bearerTokenResolver(cookieTokenResolver)) // Habilita o cookie para autenticação IMPORTANTE
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
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

    @Bean
    public RSAPublicKey publicKey() {
        try {
            ClassPathResource resource = new ClassPathResource("app.pub");
            String publicKeyPem;
            try (InputStream inputStream = resource.getInputStream()) {
                publicKeyPem = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }

            publicKeyPem = publicKeyPem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] keyBytes = Base64.getDecoder().decode(publicKeyPem);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return (RSAPublicKey) keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load public key", e);
        }
    }

    @Bean
    public RSAPrivateKey privateKey() {
        try {
            // Carrega o recurso do classpath
            ClassPathResource resource = new ClassPathResource("app.key");
            String privateKeyPem;

            // Lê o conteúdo do arquivo de forma segura (funciona dentro do JAR também)
            try (InputStream inputStream = resource.getInputStream()) {
                privateKeyPem = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }

            // Remove cabeçalhos, rodapés e espaços em branco
            privateKeyPem = privateKeyPem
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            // Decodifica a chave Base64
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyPem);

            // Especificação para chave privada PKCS#8
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return (RSAPrivateKey) keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load private key", e);
        }
    }

}
