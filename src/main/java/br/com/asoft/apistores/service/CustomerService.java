package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.CustomerDto;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.CustomerFilter;
import br.com.asoft.apistores.mapper.AddressMapper;
import br.com.asoft.apistores.mapper.CustomerMapper;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.model.Customer;
import br.com.asoft.apistores.respository.CustomerRepository;
import br.com.asoft.apistores.specifications.CustomerSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public Page<CustomerDto> allCustomerPage(CustomerFilter customerFilter, Pageable pageable) {
       Page<Customer> customer =  customerRepository.findAll(CustomerSpec.filter(customerFilter),pageable);
       List<CustomerDto> customerDto = customerMapper.toListCustomerDto(customer.getContent()); //.getContent() Importante
       Page<CustomerDto> customerDtoPage = new PageImpl<>(customerDto,pageable,customer.getTotalElements()); // new PageImpl<> e .getTotalElements() Importante
       return customerDtoPage;
    }

    public Customer findId(Long id) {
        return tryOrFail(id);
    }

    @Transactional
    public CustomerDto saveCustomerWithAddress(CustomerDto customerDto) {
        Customer customer = customerMapper.toCustomer(customerDto); //Converte o Dto para o Objeto Customer
        if(customerDto.getAddress() != null) {
            Address address = addressService.saveAddress(addressMapper.toAddress(customerDto.getAddress())); //Salva o endereco
            customer.setAddress(address); //Associa o endereco ao cliente
        }
        customer.setDateRegister(LocalDateTime.now());//Adiciona a data de cadastro
        return customerMapper.toCustomerDto(customerRepository.save(customer));
    }
    @Transactional
    public CustomerDto updateCustomerWithAddress(Long id, CustomerDto customerDto) {
        Customer customerUpdate = tryOrFail(id);

        if (customerDto.getAddress() != null) {
            Address newAddress = addressMapper.toAddress(customerDto.getAddress());
            Address savedAddress = addressService.saveAddress(newAddress);
            customerUpdate.setAddress(savedAddress);
        }
        //Garanta que copyToCustomer NÃO sobrescreve .setAddress()
        customerMapper.copyToCustomer(customerDto, customerUpdate);

        customerUpdate.setDateUpdate(LocalDateTime.now());

        Customer savedCustomer = customerRepository.save(customerUpdate);
        return customerMapper.toCustomerDto(savedCustomer);
    }

    public void deleteCliente(Long id){
        Customer customer = findId(id);
        customerRepository.delete(customer);
        customerRepository.flush();
    }

    public Customer tryOrFail(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Client",id));
    }
}
