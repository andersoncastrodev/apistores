package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.ClientMapper;
import br.com.asoft.apistores.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final ClientMapper clientMapper;

//    @GetMapping
//    public Page<ClienteOut> todosClientes(ClientFilter clientFilter, Pageable pageable) {
//
//
//        Page<Client> clientePage = clientService.allClientePage(clientFilter, pageable);
//
//        List<ClienteOut> clienteOutList = clientMapper.toListClienteOut(clientePage.getContent());
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
////        return clientMapper.toListClienteOut(clientService.findAllCliente());
////    }
////    }
//
//    @GetMapping("/{id}")
//    public ClienteOut buscarPorId(@PathVariable Long id){
//        return clientMapper.toClienteOut(clientService.findId(id));
//    }
//
//    @PostMapping
//    public ClienteOut salvaCliente(@RequestBody @Valid ClientInp clientInp) {
//        Client client = clientMapper.toCliente(clientInp);
//        return clientMapper.toClienteOut(clientService.saveCliente(client));
//    }
//
//    @PutMapping("/{id}")
//    public ClienteOut atualizaCliente(@RequestBody @Valid ClientInp clientInp, @PathVariable Long id){
//
//        Client clientAtual = clientService.findId(id);
//
//        Client clientUpdate = clientMapper.copyToCliente(clientInp, clientAtual);
//
//        return clientMapper.toClienteOut(clientService.saveCliente(clientUpdate));
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
