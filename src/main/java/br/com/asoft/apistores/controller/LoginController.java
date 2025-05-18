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
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {

    private final TokenService tokenService;

    //Faz o Login e Gera o Token JWT
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = tokenService.gerarToken(loginRequest);

        // Create cookies for both tokens
        ResponseCookie accessTokenCookie = createAccessTokenCookie(loginResponse.getAccessToken(), loginResponse.getExpiresToken());
        ResponseCookie refreshTokenCookie = createRefreshTokenCookie(loginResponse.getRefreshToken(), loginResponse.getExpiresRefToken());

        System.out.println("Enviando cookie access_token: " + accessTokenCookie.toString());
        System.out.println("Enviando cookie refresh_token: " + refreshTokenCookie.toString());

        // Set cookies in response headers
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(loginResponse); // Still include tokens in body for compatibility
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

    // Valida o Refresh Token JWT
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@CookieValue(name = "refresh_token", required = false) String refreshToken) {
        // Use cookie for refresh instead of request body
        if (refreshToken == null) {
            return ResponseEntity.badRequest().build();
        }

        LoginResponse response = tokenService.gerarTokenComRefresh(refreshToken);

        // Create new access token cookie
        ResponseCookie accessTokenCookie = createAccessTokenCookie(response.getAccessToken(), response.getExpiresToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .body(response);
    }

    // Validar token (endpoint para o middleware do Next.js)
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@CookieValue(name = "access_token", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(401).build();
        }

        try {
            tokenService.validateToken(token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    // Logout endpoint to clear cookies
    @PostMapping("/logout")
    //public ResponseEntity<Void> logout(//HttpServletResponse response) {
    public ResponseEntity<Void> logout() {
        ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", "")
                .httpOnly(true)//Segurança do token
                .secure(false) // Setar para true em produção
                .path("/")
                .maxAge(0)
                .sameSite("Lax") // 'Strict' ou ('Lax' melhor para fluxos entre paginas )
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)//Segurança do token
                .secure(false) // Setar para true em produção
                .path("/")
                .maxAge(0)
                .sameSite("Lax") // 'Strict' ou ('Lax' melhor para fluxos entre paginas )
                .build();

            // Adiciona headers para evitar cache
//            response.setHeader("Cache-Control", "no-store, must-revalidate");
//            response.setHeader("Pragma", "no-cache");
//            response.setHeader("Expires", "0");

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

    private ResponseCookie createAccessTokenCookie(String token, Long expiresIn) {
        return ResponseCookie.from("access_token", token)
                .httpOnly(true) //Segurança do token
                .secure(false) // Setar para true em produção
                .path("/")
                .maxAge(expiresIn)
                .sameSite("Lax") // 'Strict' ou ('Lax' melhor para fluxos entre paginas )
                .build();
    }

    private ResponseCookie createRefreshTokenCookie(String token, Long expiresIn) {
        return ResponseCookie.from("refresh_token", token)
                .httpOnly(true)//Segurança do token
                .secure(false) // Setar para true em produção
                .path("/")
                .maxAge(expiresIn)
                .sameSite("Lax") // 'Strict' ou ('Lax' melhor para fluxos entre paginas )
                .build();
    }

    //Valida o token JWT se foi gerado pela API. - ANTIGO NÃO APAGAR AGORA
//    @GetMapping("/validate")
//    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorization) {
//        try {
//            String token = authorization.replace("Bearer ", "");
//            Map<String, Object> claims = tokenService.validateToken(token);
//            return ResponseEntity.ok(claims); // Se quiser retornar apenas 200, pode usar ResponseEntity.ok().build()
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
//        }
//    }

    //Valida o Refresh Token jwt que foi gerado pela API - ANTIGO NÃO APAGAR AGORA
//    @PostMapping("/refresh")
//    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
//        LoginResponse response = tokenService.gerarTokenComRefresh(refreshRequest.getRefreshToken());
//        return ResponseEntity.ok(response);
//    }
}
