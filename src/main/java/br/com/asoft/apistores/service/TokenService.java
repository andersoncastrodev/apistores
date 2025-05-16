package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Roles;
import br.com.asoft.apistores.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;

    private final JwtDecoder jwtDecoder;

    private final UsersService usersService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder; //Cria uma maneira de encripar as senhas do usuario

    public LoginResponse gerarToken(LoginRequest loginRequest) {

        //Verificar se o login e senha estão corretos

        Users user = usersService.findByLogin(loginRequest.getUsername());

        boolean matches = bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if (!matches) {
            throw new EntityNotFoundExceptions("Senha Incorreta");
        }

        var now = Instant.now(); // hora atual
        var expiresIn = 1800L; // 300L 5 minutos , 900L 15 minutos, 1800L 30 minutos
        var refreshTokenExpiresIn = 86400L; // 24 horas

        // Montagem do Access Token

        //Pegar os papeis,Permissoes (Roles) do usuario
        var scopes = user.getRoles().stream()
                .map(Roles::getName)
                .collect(Collectors.toUnmodifiableList()); //Monta uma lista

        var accessTokenClaims = JwtClaimsSet.builder()
                .issuer("https://asoftsistemas.com.br")
                .subject(user.getId().toString())
                .claim("scope", scopes) // adicionando os papeis,Permissoes (Roles) do usuario
                .issuedAt(now)
                .issuedAt(now.plusSeconds(expiresIn))
                .build();

        var accessToken =  jwtEncoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();


        // Gera o Refresh Token (sem scope, apenas para renovar)
        var refreshTokenClaims = JwtClaimsSet.builder()
                .issuer("https://asoftsistemas.com.br")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(refreshTokenExpiresIn))
                .build();

        var refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();

        // Retornar objeto e o token
        return new LoginResponse(accessToken,refreshToken, expiresIn);

    }

    //Metodo para validar o token JWT se foi gerado pela API
    public Map<String, Object> validateToken(String token) {
        try {
            Jwt decodedJwt = jwtDecoder.decode(token); // Usa o JwtDecoder do Spring

            Map<String, Object> claims = new HashMap<>();
            claims.put("subject", decodedJwt.getSubject());
            claims.put("issuer", decodedJwt.getIssuer());
            claims.put("issuedAt", decodedJwt.getIssuedAt());
            claims.put("expiresAt", decodedJwt.getExpiresAt());
            claims.put("scope", decodedJwt.getClaim("scope"));

            return claims;

        } catch (JwtException e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }


    public LoginResponse gerarTokenComRefresh(String refreshToken) {

        Jwt decodedToken;

        try {
            decodedToken = jwtDecoder.decode(refreshToken);
        } catch (JwtException e) {
            throw new EntityNotFoundExceptions("Refresh token inválido ou expirado.");
        }

        // Recupera o usuário com base no subject do token
        Long userId = Long.valueOf(decodedToken.getSubject());
        Users user = usersService.findById(userId); // certifique-se que esse método existe

        var now = Instant.now();
        var accessTokenExpiresIn = 900L; //300L 5 minutos , 900L 15 minutos, 1800L 30 minutos

        var scopes = user.getRoles().stream()
                .map(Roles::getName)
                .collect(Collectors.toUnmodifiableList());

        var accessTokenClaims = JwtClaimsSet.builder()
                .issuer("https://asoftsistemas.com.br")
                .subject(user.getId().toString())
                .claim("scope", scopes)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(accessTokenExpiresIn))
                .build();

        var accessToken = jwtEncoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();

        return new LoginResponse(accessToken, refreshToken, accessTokenExpiresIn);
    }

}
