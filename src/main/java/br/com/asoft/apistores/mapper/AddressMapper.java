package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.AddressDto;
import br.com.asoft.apistores.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

//    EnderecoOut toEnderecoOut(Address address);
//
      Address toAddress(AddressDto addressDto);
//
//    Address copyToEndereco(AddressInp addressInp, @MappingTarget Address address);
//
//    List<EnderecoOut> toListEnderecoOut(List<Address> addresses);
}
