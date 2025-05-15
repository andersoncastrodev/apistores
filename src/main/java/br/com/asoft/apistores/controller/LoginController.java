package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    //Faz o Login e Gera o Token JWT
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = tokenService.gerarToken(loginRequest);

        return ResponseEntity.ok().body(loginResponse);
    }

    //Valida o token JWT se foi gerado pela API.
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("authToken") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            Map<String, Object> claims = tokenService.validateToken(token);
            return ResponseEntity.ok(claims); // Se quiser retornar apenas 200, pode usar ResponseEntity.ok().build()
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

}
