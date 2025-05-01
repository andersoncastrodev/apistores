package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.UsuarioInp;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.out.UsuarioOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioOut toUsuarioOut(Users users);

    Users toUsuario(UsuarioInp usuarioInp);

    Users copyToUsuario(UsuarioInp usuarioInp, @MappingTarget Users users);

    List<UsuarioOut> toListUsuarioOut(List<Users> users);
}
