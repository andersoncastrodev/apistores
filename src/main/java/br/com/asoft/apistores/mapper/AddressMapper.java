package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.AddressDto;
import br.com.asoft.apistores.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressDto addressDto);
}
