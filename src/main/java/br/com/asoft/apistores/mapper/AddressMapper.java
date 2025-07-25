package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.AddressDto;
import br.com.asoft.apistores.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {
//    EnderecoOut toEnderecoOut(Address address);

    @Mapping(target = "id", ignore = true)
    Address toAddress(AddressDto addressDto);
    
    @Mapping(target = "id", ignore = true)
    void updateAddressFromDto(AddressDto dto, @MappingTarget Address entity);
    
    // List<EnderecoOut> toListEnderecoOut(List<Address> addresses);
}
