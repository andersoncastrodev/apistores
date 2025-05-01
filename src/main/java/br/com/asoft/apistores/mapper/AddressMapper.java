package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.EnderecoInp;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.out.EnderecoOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    EnderecoOut toEnderecoOut(Address address);

    Address toEndereco(EnderecoInp enderecoInp);

    Address copyToEndereco(EnderecoInp enderecoInp, @MappingTarget Address address);

    List<EnderecoOut> toListEnderecoOut(List<Address> addresses);
}
