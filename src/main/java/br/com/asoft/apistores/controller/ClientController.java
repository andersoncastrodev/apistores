package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.ClientFilter;
import br.com.asoft.apistores.inp.ClienteInp;
import br.com.asoft.apistores.mapper.ClientMapper;
import br.com.asoft.apistores.model.Client;
import br.com.asoft.apistores.out.ClienteOut;
import br.com.asoft.apistores.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @GetMapping
    public Page<ClienteOut> todosClientes(ClientFilter clientFilter, Pageable pageable) {


        Page<Client> clientePage = clientService.allClientePage(clientFilter, pageable);

        List<ClienteOut> clienteOutList = clientMapper.toListClienteOut(clientePage.getContent());

        Page<ClienteOut> clientePageOut = new PageImpl<>(clienteOutList,pageable,clientePage.getTotalPages());

        return clientePageOut;

    }

// SEM PAGINACAO
//    @GetMapping
//   public List<ClienteOut> todosClientes(){
//        return clientMapper.toListClienteOut(clientService.findAllCliente());
//    }
//    }

    @GetMapping("/{id}")
    public ClienteOut buscarPorId(@PathVariable Long id){
        return clientMapper.toClienteOut(clientService.findId(id));
    }

    @PostMapping
    public ClienteOut salvaCliente(@RequestBody @Valid ClienteInp clienteInp){
        Client client = clientMapper.toCliente(clienteInp);
        return clientMapper.toClienteOut(clientService.saveCliente(client));
    }

    @PutMapping("/{id}")
    public ClienteOut atualizaCliente(@RequestBody @Valid ClienteInp clienteInp, @PathVariable Long id){

        Client clientAtual = clientService.findId(id);

        Client clientUpdate = clientMapper.copyToCliente(clienteInp, clientAtual);

        return clientMapper.toClienteOut(clientService.saveCliente(clientUpdate));

    }

    @DeleteMapping("/{id}")
    //@Operation(summary = "Busca por Codigo")
    public void excluirCliente(@PathVariable Long id){
        clientService.deleteCliente(id);
    }

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
