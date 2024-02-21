package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.UsuarioInp;
import br.com.asoft.apistores.mapper.UsuarioMapper;
import br.com.asoft.apistores.model.Usuario;
import br.com.asoft.apistores.out.UsuarioOut;
import br.com.asoft.apistores.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;


    @GetMapping
    public Page<UsuarioOut> listaUsuario(Pageable pageable){

        Page<Usuario> usuariosPage = usuarioService.allTodos(pageable);
        List<UsuarioOut> usuarioOutsList = usuarioMapper.toListUsuarioOut(usuariosPage.getContent());
        Page<UsuarioOut> usuarioOutPage = new PageImpl<>(usuarioOutsList,pageable,usuariosPage.getTotalPages());
        return null;
    }

//    @GetMapping
//    public List<UsuarioOut> listarUsuarios() {
//        return usuarioMapper.toListUsuarioOut( usuarioService.allTodos());
//    }

    @GetMapping("/{id}")
    public UsuarioOut buscaPorId(@PathVariable Long id){
        return usuarioMapper.toUsuarioOut(usuarioService.findId(id));
    }

    @PostMapping
    public UsuarioOut salvarUsuario(@RequestBody @Valid UsuarioInp usuarioInp ){
        Usuario usuario = usuarioService.saveUsuario( usuarioMapper.toUsuario(usuarioInp) );
        return usuarioMapper.toUsuarioOut(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioOut alterarUsuario(@RequestBody @Valid UsuarioInp usuarioInp, @PathVariable Long id){

        Usuario usuarioAtual = usuarioService.findId(id);

        Usuario usuarioNovo = usuarioMapper.copyToUsuario(usuarioInp,usuarioAtual);

        return usuarioMapper.toUsuarioOut(usuarioService.saveUsuario(usuarioNovo));

    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id){
          usuarioService.deleteUsuario(id);
    }

    @GetMapping("/relatoriousuarios")
    public ResponseEntity<InputStreamResource> relatorioUsuarios(){

        try {

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "inline; filename=vendas.pdf");

        InputStreamResource relatorio = new InputStreamResource( usuarioService.relatorioUsuarios());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
