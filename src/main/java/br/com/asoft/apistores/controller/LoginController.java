package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.LoginInp;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.out.LoginOut;
import br.com.asoft.apistores.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private UsersService usersService;

    @PostMapping
    public ResponseEntity<LoginOut> login(@RequestBody LoginInp loginInp) {

        boolean loginCorrect = usersService.isLoginCorrect(loginInp.getUsername(), loginInp.getPassword());

        if (!loginCorrect) {
            throw new BadCredentialsException("Usuário não encontrado");
        }
        return ResponseEntity.ok().build();
    }


}
