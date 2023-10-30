package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.UsuarioMapper;
import br.com.asoft.apistores.out.UsuarioOut;
import br.com.asoft.apistores.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;

    @GetMapping
    public List<UsuarioOut> listarUsuarios(){
        return usuarioMapper.toListUsuarioOut( usuarioService.allTodos());
    }

    @GetMapping("/{id}")
    public UsuarioOut buscaPorId(@PathVariable Long id){
        return usuarioMapper.toUsuarioOut(usuarioService.findId(id));
    }

}
