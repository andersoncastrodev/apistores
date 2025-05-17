package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.dto.RefreshRequest;
import br.com.asoft.apistores.service.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
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

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
//
//        LoginResponse loginResponse = tokenService.gerarToken(loginRequest);
//
//        // Configura o refreshToken como HttpOnly cookie
//        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", loginResponse.getRefreshToken())
//                .httpOnly(true)
//                .secure(false) // Em produção deve ser true
//                .path("/")
//                .maxAge(Duration.ofSeconds(loginResponse.getExpiresRefToken()))
//                .sameSite("Strict")
//                .build();
//
//        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
//
//        return ResponseEntity.ok().body(loginResponse);
//    }

//        @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse res) {
//
//        LoginResponse response = tokenService.gerarToken(loginRequest);
//
//        // Configura cookies
//        ResponseCookie tokenCookie = ResponseCookie.from("token", response.getAccessToken())
//                .httpOnly(false)
//                .secure(false) // true em produção
//                .path("/")
//                .maxAge(Duration.ofSeconds(response.getExpiresToken()))
//                .sameSite("Strict")
//                .build();
//
//        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", response.getRefreshToken())
//                .httpOnly(true) // RefreshToken deve ser HttpOnly
//                .secure(false)
//                .path("/")
//                .maxAge(Duration.ofSeconds(response.getExpiresRefToken()))
//                .sameSite("Strict")
//                .build();
//
//        res.addHeader(HttpHeaders.SET_COOKIE, tokenCookie.toString());
//        res.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
//
//        return ResponseEntity.ok(response);
//    }

    //Valida o token JWT se foi gerado pela API.
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorization) {
        try {
            String token = authorization.replace("Bearer ", "");
            Map<String, Object> claims = tokenService.validateToken(token);
            return ResponseEntity.ok(claims); // Se quiser retornar apenas 200, pode usar ResponseEntity.ok().build()
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

    //Valida o Refresh Token jwt que foi gerado pela API
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        LoginResponse response = tokenService.gerarTokenComRefresh(refreshRequest.getRefreshToken());
        return ResponseEntity.ok(response);
    }
}
