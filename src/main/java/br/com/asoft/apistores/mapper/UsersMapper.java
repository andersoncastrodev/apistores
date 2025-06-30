package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.UsersDto;
import br.com.asoft.apistores.model.Users;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDto toUsersDto(Users users);

    Users toUsers(UsersDto usersDto);

    //Users copyToUsuario(UsuarioInp usuarioInp, @MappingTarget Users users);

    List<UsersDto> toListUsersDto(List<Users> users);
}
