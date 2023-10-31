package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.UsuarioInp;
import br.com.asoft.apistores.model.Usuario;
import br.com.asoft.apistores.out.UsuarioOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioOut toUsuarioOut(Usuario usuario);

    Usuario toUsuario(UsuarioInp usuarioInp);

    List<UsuarioOut> toListUsuarioOut(List<Usuario> usuarios);
}
