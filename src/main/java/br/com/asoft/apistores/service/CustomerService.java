package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.CustomerDto;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.ClientFilter;
import br.com.asoft.apistores.mapper.AddressMapper;
import br.com.asoft.apistores.mapper.CustomerMapper;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.model.Customer;
import br.com.asoft.apistores.respository.CustomerRepository;
import br.com.asoft.apistores.specifications.ClienteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Page<Customer> allClientePage(ClientFilter clientFilter, Pageable pageable){

        return customerRepository.findAll(ClienteSpecification.filter(clientFilter),pageable);
    }

    public Page<Customer> allClientePage2(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public List<Customer> findAllCliente() {
        return customerRepository.findAll();
    }

    public Customer findId(Long id){
        return tryOrFail(id);
    }


    public CustomerDto saveCustomerWithAddress(CustomerDto customerDto) {
        Customer customer = customerMapper.toCustomer(customerDto); //Converte o Dto para o Objeto Customer
        if(customerDto.getAddress() != null) {
            Address address = addressService.saveAddress(addressMapper.toAddress(customerDto.getAddress())); //Salva o endereco
            customer.setAddress(address); //Associa o endereco ao cliente
        }
        customer.setDateRegister(LocalDateTime.now());//Adiciona a data de cadastro
        return customerMapper.toCustomerDto(customerRepository.save(customer));
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
