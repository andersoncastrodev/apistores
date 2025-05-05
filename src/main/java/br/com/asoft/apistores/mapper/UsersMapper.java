package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.UsersRequest;
import br.com.asoft.apistores.dto.UsersResponse;
import br.com.asoft.apistores.inp.UsuarioInp;
import br.com.asoft.apistores.model.Users;
import br.com.asoft.apistores.out.UsuarioOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersResponse toUsersResponse(Users users);

    Users toUsers(UsersRequest usersRequest);

    Users copyToUsuario(UsuarioInp usuarioInp, @MappingTarget Users users);

    List<UsersResponse> toListUsersResponse(List<Users> users);
}
