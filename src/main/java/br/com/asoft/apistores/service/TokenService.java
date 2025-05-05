package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public LoginResponse gerarToken(LoginRequest loginRequest) {

        var now = Instant.now(); // hora atual
        var expiresIn = 300L; // 5 minutos tempo do expira o token

        // Montagem do token
        var clains = JwtClaimsSet.builder()
                .issuer("Asoft-Sistemas")
                .subject(loginRequest.getUsername())
                .issuedAt(now)
                .issuedAt(now.plusSeconds(expiresIn))
                .build();

        // Gerar o token
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(clains));

        // Retornar objeto e o token
        return new LoginResponse(jwtValue.getTokenValue(), expiresIn);

    }
}
