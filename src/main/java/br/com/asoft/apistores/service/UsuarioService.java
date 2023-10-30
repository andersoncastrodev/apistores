package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Usuario;

import br.com.asoft.apistores.respository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> allTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario findId(Long id){
        return tryOrFail(id);
    }


    public Usuario tryOrFail(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundExceptions("Usuario",id) );
    }
}
