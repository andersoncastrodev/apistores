package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.dto.RefreshResponse;
import br.com.asoft.apistores.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail().equals("") || loginRequest.getPassword().equals("")) {
            return ResponseEntity.badRequest().build();
        }

        LoginResponse loginResponse = tokenService.gerarToken(loginRequest);

        System.out.println("Login realizado com sucesso para: " + loginRequest.getEmail());

        // Retornar apenas o JSON com os tokens
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@CookieValue(name = "refresh_token", required = false) String refreshToken) {
        System.out.println("Refresh token recebido: " + (refreshToken != null ? "Presente" : "Ausente"));

        if (refreshToken == null) {
            return ResponseEntity.status(401).build();
        }

        try {
            LoginResponse response = tokenService.gerarTokenComRefresh(refreshToken);

            // Retornar apenas o novo access token
            RefreshResponse refreshResponse = new RefreshResponse(
                    response.getAccessToken(),
                    response.getExpiresToken()
            );

            return ResponseEntity.ok(refreshResponse);
        } catch (Exception e) {
            System.out.println("Erro no refresh: " + e.getMessage());
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@CookieValue(name = "access_token", required = false) String token) {
        System.out.println("Validando token: " + (token != null ? "Presente" : "Ausente"));

        if (token == null) {
            return ResponseEntity.status(401).build();
        }

        try {
            tokenService.validateToken(token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erro na validação do token: " + e.getMessage());
            return ResponseEntity.status(401).build();
        }
    }
}
