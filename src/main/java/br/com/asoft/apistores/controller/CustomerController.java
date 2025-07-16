package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.CustomerDto;
import br.com.asoft.apistores.mapper.AddressMapper;
import br.com.asoft.apistores.mapper.CustomerMapper;
import br.com.asoft.apistores.model.*;
import br.com.asoft.apistores.service.AddressService;
import br.com.asoft.apistores.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        // Salvando o endereço
        Address address = null;
        if (customerDto.getAddress() != null) {
            address = addressService.saveAddress(addressMapper.toAddress(customerDto.getAddress()));
        }
        Customer customer = customerMapper.toCustomer(customerDto);
        customer.setAddress(address);
        return customerMapper.toCustomerDto(customerService.saveCustomer(customer));

    }

//    @GetMapping
//    public Page<ClienteOut> todosClientes(ClientFilter clientFilter, Pageable pageable) {
//
//
//        Page<Client> clientePage = clientService.allClientePage(clientFilter, pageable);
//
//        List<ClienteOut> clienteOutList = customerMapper.toListClienteOut(clientePage.getContent());
//
//        Page<ClienteOut> clientePageOut = new PageImpl<>(clienteOutList,pageable,clientePage.getTotalPages());
//
//        return clientePageOut;
//
//    }
//
//// SEM PAGINACAO
////    @GetMapping
////   public List<ClienteOut> todosClientes(){
////        return customerMapper.toListClienteOut(clientService.findAllCliente());
////    }
////    }
//
//    @GetMapping("/{id}")
//    public ClienteOut buscarPorId(@PathVariable Long id){
//        return customerMapper.toClienteOut(clientService.findId(id));
//    }
//
//    @PostMapping
//    public ClienteOut salvaCliente(@RequestBody @Valid ClientInp clientInp) {
//        Client client = customerMapper.toCliente(clientInp);
//        return customerMapper.toClienteOut(clientService.saveCliente(client));
//    }
//
//    @PutMapping("/{id}")
//    public ClienteOut atualizaCliente(@RequestBody @Valid ClientInp clientInp, @PathVariable Long id){
//
//        Client clientAtual = clientService.findId(id);
//
//        Client clientUpdate = customerMapper.copyToCliente(clientInp, clientAtual);
//
//        return customerMapper.toClienteOut(clientService.saveCliente(clientUpdate));
//
//    }
//
//    @DeleteMapping("/{id}")
//    //@Operation(summary = "Busca por Codigo")
//    public void excluirCliente(@PathVariable Long id){
//        clientService.deleteCliente(id);
//    }

//    @GetMapping("/relatoriocliente")
//    public ResponseEntity<InputStreamResource> relatorioClientes() {
//
//        try {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=pessoas.pdf");
//
//        InputStreamResource relatorio = new InputStreamResource( clientService.relatorioCliente());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(relatorio);
//
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }


}
