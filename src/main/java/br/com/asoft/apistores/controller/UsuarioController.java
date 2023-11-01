package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.UsuarioInp;
import br.com.asoft.apistores.mapper.UsuarioMapper;
import br.com.asoft.apistores.model.Usuario;
import br.com.asoft.apistores.out.UsuarioOut;
import br.com.asoft.apistores.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public UsuarioOut salvarUsuario(@RequestBody @Valid UsuarioInp usuarioInp ){
        Usuario usuario = usuarioService.saveUsuario( usuarioMapper.toUsuario(usuarioInp) );
        return usuarioMapper.toUsuarioOut(usuario);
    }

    @PutMapping("/id")
    public UsuarioOut alterarUsuario(@RequestBody @Valid UsuarioInp usuarioInp, @PathVariable Long id){

        Usuario usuarioAtual = usuarioService.findId(id);

        Usuario usuarioNovo = usuarioMapper.copyToUsuario(usuarioInp,usuarioAtual);

        return usuarioMapper.toUsuarioOut(usuarioService.saveUsuario(usuarioNovo));

    }

}
