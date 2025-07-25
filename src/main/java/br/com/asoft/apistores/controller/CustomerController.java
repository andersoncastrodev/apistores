package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.CustomerDto;
import br.com.asoft.apistores.filter.CustomerFilter;
import br.com.asoft.apistores.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Page<CustomerDto> findCustomers(CustomerFilter customerFilter, Pageable pageable) {
        return customerService.allCustomerPage(customerFilter, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return customerService.saveCustomerWithAddress(customerDto);
    }
    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDto customerDto) {
        return customerService.updateCustomerWithAddress(id, customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerWithAddress(id);
    }

}
