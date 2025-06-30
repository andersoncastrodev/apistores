package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.ClientDto;
import br.com.asoft.apistores.model.Client;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(ClientDto clientDto);

    ClientDto toClienteDto(Client client);

    //Client copyToCliente(ClientDto clientDto, @MappingTarget Client client);

    List<ClientDto> toListClienteDto(List<Client> clients);

}
