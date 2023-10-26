package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.out.UsuarioOut;
import br.com.asoft.apistores.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    public List<UsuarioOut> listarTodos(){

    }

}
