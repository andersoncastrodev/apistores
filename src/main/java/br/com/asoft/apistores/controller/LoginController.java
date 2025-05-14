package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse loginResponse = tokenService.gerarToken(loginRequest);

        return ResponseEntity.ok().body(loginResponse); //
    }


}
