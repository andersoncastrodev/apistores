package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.CustomerDto;
import br.com.asoft.apistores.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerDto customerDto);

    CustomerDto toCustomerDto(Customer customer);

    Customer copyToCustomer(CustomerDto customerDto, @MappingTarget Customer customer);

    List<CustomerDto> toListCustomerDto(List<Customer> customers);

}
