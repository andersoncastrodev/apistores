package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.CustomerDto;
import br.com.asoft.apistores.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);

    @Mapping(target = "address", ignore = true)
    Customer copyToCustomer(CustomerDto customerDto, @MappingTarget Customer customer);

    List<CustomerDto> toListCustomerDto(List<Customer> customers);

}
