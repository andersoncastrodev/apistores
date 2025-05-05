package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.LoginRequest;
import br.com.asoft.apistores.dto.LoginResponse;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.service.TokenService;
import br.com.asoft.apistores.service.UsersService;
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

    private final UsersService usersService;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        //Validada do login usuario e senha
        boolean loginCorrect = usersService.isLoginCorrect(loginRequest.getUsername(), loginRequest.getPassword());

        if (!loginCorrect) {
            //throw new BadCredentialsException("Usuário não encontrado");
            throw new EntityNotFoundExceptions("Usuário não encontrado");
        }

        LoginResponse loginResponse = tokenService.gerarToken(loginRequest);

        return ResponseEntity.ok().body(loginResponse);
    }


}
