package br.com.asoft.apistores.service;

import br.com.asoft.apistores.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class TokenService {

    private UsuarioService usuarioService;

    public String gerarToken(Usuario usuario) {

        var user = usuarioService.findId(usuario.getId());

//        var now = Instant.now();
//        var expiresIn = 46000L;
//
//        var scope = "read write";

        return null;
    }
}
