package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.UsersRequest;
import br.com.asoft.apistores.dto.UsersResponse;
import br.com.asoft.apistores.mapper.UsersMapper;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    private final UsersMapper usersMapper;

    @GetMapping
    public Page<UsersResponse> listUsers(Pageable pageable) {
        Page<Users> usersPage = usersService.findAllUsers(pageable);
        List<UsersResponse> usersResponseList = usersMapper.toListUsersResponse(usersPage.getContent());
        Page<UsersResponse> usersResponsePage = new PageImpl<>(usersResponseList,pageable,usersPage.getTotalPages());
        return usersResponsePage;
    }

//    @GetMapping
//    public List<UsuarioOut> listarUsuarios() {
//        return usersMapper.toListUsuarioOut( usersService.allTodos());
//    }

//    @GetMapping("/{id}")
//    public UsuarioOut buscaPorId(@PathVariable Long id){
//        return usersMapper.toUsuarioOut(usersService.findId(id));
//    }

    @PostMapping
    public UsersResponse saveUsers(@RequestBody @Valid UsersRequest usersRequest ) {
        Users users = usersService.saverUsers( usersMapper.toUsers(usersRequest) );
        return usersMapper.toUsersResponse(users);
    }

//    @PutMapping("/{id}")
//    public UsuarioOut alterarUsuario(@RequestBody @Valid UsuarioInp usuarioInp, @PathVariable Long id){
//
//        Users usersAtual = usersService.findId(id);
//
//        Users usersNovo = usersMapper.copyToUsuario(usuarioInp, usersAtual);
//
//        return usersMapper.toUsuarioOut(usersService.saveUsuario(usersNovo));
//
//    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id){
          usersService.deleteUsuario(id);
    }

//    @GetMapping("/relatoriousuarios")
//    public ResponseEntity<InputStreamResource> relatorioUsuarios(){
//
//        try {
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.add("Content-Disposition", "inline; filename=vendas.pdf");
//
//        InputStreamResource relatorio = new InputStreamResource( usersService.relatorioUsuarios());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(relatorio);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }

}
