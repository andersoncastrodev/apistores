package br.com.asoft.apistores.infra;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
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

//    @Value("${jwt.public.key}")
//    private RSAPublicKey publicKey;
//
//    @Value("${jwt.private.key}")
//    private RSAPrivateKey privateKey;

    private final CorsFilter corsFilter;

    private final CookieTokenResolver cookieTokenResolver;

    public SecurityConfig(CorsFilter corsFilter, CookieTokenResolver cookieTokenResolver) {
        this.corsFilter = corsFilter;
        this.cookieTokenResolver = cookieTokenResolver;
    }

    @Bean
    public RSAPublicKey publicKey() {
        try {
            // Conteúdo da chave pública diretamente no código
            String publicKeyPem = """
                    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtrSdSV4gYhN4ogW4rW1Y
                    9C2Hj+JBULpPjsSeYlAPzpMnI3v8/OTt7K0YeT89nMEG37wmiMrB9MsBan37wwtZ
                    NLuN4DlR5bYtRz+rDjp9Ul3f3JrPuLVCHSUedx72jCzp9v95FUXtv73dLzwCcJLO
                    VE1v8JCgwyBanu/0awSL8CnCL7/ONDMMZr9r41PdrdtwRAxuh9g0FlhGTA4+hJQo
                    waqjZTJEaZb0aS6H2xnOa3rfbDspaSSQbBZNB8VG/S3yMVfN2F01L4yIxpMe8WW8
                    8N5ZbB4lwKSlO0DM9gWoV3bVArBiskciw7tr6DvC4lx0c3LEUX+5WUgFDSWn38bx
                    pwIDAQAB
                    """;

            // Remove quebras de linha e espaços
            publicKeyPem = publicKeyPem.replaceAll("\\s", "");

            // Decodifica a chave
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
            // Conteúdo da chave privada diretamente no código
            String privateKeyPem = """
                    MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC2tJ1JXiBiE3ii
                    BbitbVj0LYeP4kFQuk+OxJ5iUA/Okycje/z85O3srRh5Pz2cwQbfvCaIysH0ywFq
                    ffvDC1k0u43gOVHlti1HP6sOOn1SXd/cms+4tUIdJR53HvaMLOn2/3kVRe2/vd0v
                    PAJwks5UTW/wkKDDIFqe7/RrBIvwKcIvv840Mwxmv2vjU92t23BEDG6H2DQWWEZM
                    Dj6ElCjBqqNlMkRplvRpLofbGc5ret9sOylpJJBsFk0HxUb9LfIxV83YXTUvjIjG
                    kx7xZbzw3llsHiXApKU7QMz2BahXdtUCsGKyRyLDu2voO8LiXHRzcsRRf7lZSAUN
                    JaffxvGnAgMBAAECggEAB+GUo1SPGwvx+TM7+ycrXiUUou1EmgHfaq4qkt0XQXbx
                    ikNP72zEop/43UoQE1H0RPRtyRrN8QxMvFZA5PH5YB/zB8gRM1J/c4JYk52MSGBK
                    Rs7axQH0nyM5xDOuuO87DG+KvpMspUfAYcg55rc+dT595Keod6JOpmp9ZZxlmO9x
                    ZMPehXdmIh7UQaIKPQcr+SweyzPZ1FdLw62zW90JbuiucGZKQ+qbPVk/o2eG3Tpb
                    8ZGDovU07X4wWkLBQVsvxsXb82ul18ppOfIWs5Dr3KUb+G3nsgYBRExxlWzbm6FV
                    tY91vJsbg5sBbuMp+55jsW8u/RDmjz2WxfvzCPFkoQKBgQD1mDwslBylKqJkRW2j
                    MKS/C0CRt6UzQwoz1bvhu3tHd9I/weMN/4aAb7az7Hm1RqV56FSTaq/+LKm8KpTo
                    /DH3pFuXGWSg1LQwm56q6V2zTfJtmOJMN6j5IwjM+6YlBDKgZSwRkWx32aLhE5dl
                    VB2JP3ZJr3tgfa+Ku3NmI0GH9wKBgQC+ckWqYPpG3Amd5ZejWJeWHVl+hBMIxuM1
                    DoDQX6eosj7Gm4vTDm8Rudqf/BpqCDKnLKeKc2BjRtbNIheCIBhmrW/gyJsxbb+H
                    27C/8rHKSXSao4W7S8t4c1f6A2N+knqwafm9hGN8ZR0RCxowkb5keQAnTKIg4b1s
                    Ulr1rf5X0QKBgGJbyOW1n89KRuVPpPwxdBmLIR365a+lDsX5uJhMJLBXvZ2JQi0O
                    BEkV9J8Uex3toEI1mQG9PaTXMFdK2n6A2mYqrf/SzKlY5p19Bcu/UKaJ9iiMjT0n
                    IYY90L1/n2e9yaTRQBf9HOrW/9OSrBSJ3pZkEzSpkgjv7Ujrj+j8/7a7AoGANPmo
                    c0pilA9lBWz2D1P3ZqRnXo/yk4BvdzS/lDbndj+OwsRF0sGzF6UxMUt5NYFuRZ5g
                    RFzvtO3hllIjY+j1oKoh8s8ajFdQ7cOomNwzpGdmbhWKf50HzkuXb04+ANlSE3yH
                    IbaPzex6d2E1OclwwpX9+vCQMMFaZaVK9AggNeECgYAN+s9cdLKSsqyYuAnGJyh/
                    uA4filegrPUmz2u6iNUvMON45W6xLXQGN1GCYVxiV4tfAw+f80rv7mM3ue+2JMqm
                    2gvxPo43Nwkmm7Ztgrq3I5b687vGoZJb6rNXl1BwCqnqDYGtYK+da96/azSmv4WM
                    6r0oTF3ezsR9lpPbZkHAYA==
                    """;

            // Remove quebras de linha e espaços
            privateKeyPem = privateKeyPem.replaceAll("\\s", "");

            // Decodifica a chave
            byte[] keyBytes = Base64.getDecoder().decode(privateKeyPem);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(spec);

        } catch (Exception e) {
            throw new IllegalStateException("Failed to load private key", e);
        }
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
        return NimbusJwtDecoder.withPublicKey(publicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(publicKey()).privateKey(privateKey()).build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    //Cria uma maneira de encripar as senhas do usuario
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public RSAPublicKey publicKey() {
//        try {
//            ClassPathResource resource = new ClassPathResource("app.pub");
//            String publicKeyPem;
//            try (InputStream inputStream = resource.getInputStream()) {
//                publicKeyPem = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//            }
//
//            publicKeyPem = publicKeyPem
//                    .replace("-----BEGIN PUBLIC KEY-----", "")
//                    .replace("-----END PUBLIC KEY-----", "")
//                    .replaceAll("\\s", "");
//
//            byte[] keyBytes = Base64.getDecoder().decode(publicKeyPem);
//            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//            return (RSAPublicKey) keyFactory.generatePublic(spec);
//        } catch (Exception e) {
//            throw new IllegalStateException("Failed to load public key", e);
//        }
//    }
//
//    @Bean
//    public RSAPrivateKey privateKey() {
//        try {
//            // Carrega o recurso do classpath
//            ClassPathResource resource = new ClassPathResource("app.key");
//            String privateKeyPem;
//
//            // Lê o conteúdo do arquivo de forma segura (funciona dentro do JAR também)
//            try (InputStream inputStream = resource.getInputStream()) {
//                privateKeyPem = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//            }
//
//            // Remove cabeçalhos, rodapés e espaços em branco
//            privateKeyPem = privateKeyPem
//                    .replace("-----BEGIN PRIVATE KEY-----", "")
//                    .replace("-----END PRIVATE KEY-----", "")
//                    .replaceAll("\\s", "");
//
//            // Decodifica a chave Base64
//            byte[] keyBytes = Base64.getDecoder().decode(privateKeyPem);
//
//            // Especificação para chave privada PKCS#8
//            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//            return (RSAPrivateKey) keyFactory.generatePrivate(spec);
//        } catch (Exception e) {
//            throw new IllegalStateException("Failed to load private key", e);
//        }
//    }

}
