package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.UsersDto;
import br.com.asoft.apistores.mapper.UsersMapper;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    private final UsersMapper usersMapper;

    @GetMapping
    public Page<UsersDto> listUsers(Pageable pageable) {
        Page<Users> usersPage = usersService.findAllUsers(pageable);
        List<UsersDto> usersDtoList = usersMapper.toListUsersDto(usersPage.getContent());
        Page<UsersDto> usersResponsePage = new PageImpl<>(usersDtoList,pageable,usersPage.getTotalPages());
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
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public UsersDto saveUsers(@RequestBody @Valid UsersDto userDto ) {
        Users users = usersService.saverUsers( usersMapper.toUsers(userDto) );
        return usersMapper.toUsersDto(users);
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
