package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    //Faz o Login e Gera o Token JWT
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail().equals("") || loginRequest.getPassword().equals("")) {
            return ResponseEntity.badRequest().build();
        }

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
                .body(loginResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@CookieValue(name = "refresh_token", required = false) String refreshToken) {
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

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        ResponseCookie accessTokenCookie = clearCookie("access_token");
        ResponseCookie refreshTokenCookie = clearCookie("refresh_token");

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .build();
    }

    private ResponseCookie createAccessTokenCookie(String token, Long expiresIn) {
        return ResponseCookie.from("access_token", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(expiresIn)
                .sameSite("None")
                //.domain(".onrender.com") // Para funcionar entre subdomínios do Render
                .build();
    }

    private ResponseCookie createRefreshTokenCookie(String token, Long expiresIn) {
        return ResponseCookie.from("refresh_token", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(expiresIn)
                .sameSite("None")
                //.domain(".onrender.com")
                .build();
    }

    private ResponseCookie clearCookie(String cookieName) {
        return ResponseCookie.from(cookieName, "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("None")
                //.domain(".onrender.com")
                .build();
    }
}
