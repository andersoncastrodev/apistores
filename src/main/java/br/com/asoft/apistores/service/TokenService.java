package br.com.asoft.apistores.service;

import br.com.asoft.apistores.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class TokenService {

    private UsersService usersService;

    public String gerarToken(Users users) {

        var user = usersService.findId(users.getId());

        var now = Instant.now();
        var expiresIn = 46000L;

        var scope = "read write";

        return null;
    }
}
