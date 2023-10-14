package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.ClienteInp;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.out.ClienteOut;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClienteInp clienteInp);

    ClienteOut toClienteOut(Cliente cliente);

    List<ClienteOut> toListClienteOut(List<Cliente> clientes);

}
