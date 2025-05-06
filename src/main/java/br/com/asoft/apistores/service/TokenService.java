package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Roles;
import br.com.asoft.apistores.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder jwtEncoder;

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
        var expiresIn = 300L; // 5 minutos tempo do expira o token

        // Montagem do token

        //Pegar os papeis,Permissoes (Roles) do usuario
        var scopes = user.getRoles().stream()
                .map(Roles::getName)
                .collect(Collectors.toUnmodifiableList()); //Monta uma lista

        var clains = JwtClaimsSet.builder()
                .issuer("Asoft-Sistemas")
                .subject(user.getId().toString())
                .claim("scope", scopes) // adicionando os papeis,Permissoes (Roles) do usuario
                .issuedAt(now)
                .issuedAt(now.plusSeconds(expiresIn))
                .build();

        // Gerar o token
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(clains));

        // Retornar objeto e o token
        return new LoginResponse(jwtValue.getTokenValue(), expiresIn);

    }

}
