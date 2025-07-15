package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.CustomerDto;
import br.com.asoft.apistores.model.Customer;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Customer toClient(CustomerDto customerDto);

    CustomerDto toClienteDto(Customer customer);

    //Client copyToCliente(ClientDto clientDto, @MappingTarget Client client);

    List<CustomerDto> toListClienteDto(List<Customer> customers);

}
