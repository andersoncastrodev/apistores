package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.UsuarioInp;
import br.com.asoft.apistores.mapper.UsersMapper;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.out.UsuarioOut;
import br.com.asoft.apistores.service.UsersService;
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
public class UsersController {

    private final UsersService usersService;

    private final UsersMapper usersMapper;


    @GetMapping
    public Page<UsuarioOut> listaUsuario(Pageable pageable){

        Page<Users> usuariosPage = usersService.allTodos(pageable);
        List<UsuarioOut> usuarioOutsList = usersMapper.toListUsuarioOut(usuariosPage.getContent());
        Page<UsuarioOut> usuarioOutPage = new PageImpl<>(usuarioOutsList,pageable,usuariosPage.getTotalPages());
        return null;
    }

//    @GetMapping
//    public List<UsuarioOut> listarUsuarios() {
//        return usersMapper.toListUsuarioOut( usersService.allTodos());
//    }

    @GetMapping("/{id}")
    public UsuarioOut buscaPorId(@PathVariable Long id){
        return usersMapper.toUsuarioOut(usersService.findId(id));
    }

    @PostMapping
    public UsuarioOut salvarUsuario(@RequestBody @Valid UsuarioInp usuarioInp ){
        Users users = usersService.saveUsuario( usersMapper.toUsuario(usuarioInp) );
        return usersMapper.toUsuarioOut(users);
    }

    @PutMapping("/{id}")
    public UsuarioOut alterarUsuario(@RequestBody @Valid UsuarioInp usuarioInp, @PathVariable Long id){

        Users usersAtual = usersService.findId(id);

        Users usersNovo = usersMapper.copyToUsuario(usuarioInp, usersAtual);

        return usersMapper.toUsuarioOut(usersService.saveUsuario(usersNovo));

    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id){
          usersService.deleteUsuario(id);
    }

    @GetMapping("/relatoriousuarios")
    public ResponseEntity<InputStreamResource> relatorioUsuarios(){

        try {

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "inline; filename=vendas.pdf");

        InputStreamResource relatorio = new InputStreamResource( usersService.relatorioUsuarios());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
