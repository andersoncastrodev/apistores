package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

//    EnderecoOut toEnderecoOut(Address address);
//
//    Address toEndereco(AddressInp addressInp);
//
//    Address copyToEndereco(AddressInp addressInp, @MappingTarget Address address);
//
//    List<EnderecoOut> toListEnderecoOut(List<Address> addresses);
}
