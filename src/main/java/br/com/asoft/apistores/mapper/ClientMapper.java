package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.ClienteInp;
import br.com.asoft.apistores.model.Client;
import br.com.asoft.apistores.out.ClienteOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toCliente(ClienteInp clienteInp);

    ClienteOut toClienteOut(Client client);

    Client copyToCliente(ClienteInp clienteInp, @MappingTarget Client client);

    List<ClienteOut> toListClienteOut(List<Client> clients);

}
