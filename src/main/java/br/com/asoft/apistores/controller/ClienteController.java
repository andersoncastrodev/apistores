package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.ClienteFilter;
import br.com.asoft.apistores.inp.ClienteInp;
import br.com.asoft.apistores.mapper.ClienteMapper;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.out.ClienteOut;
import br.com.asoft.apistores.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

    @GetMapping
    public Page<ClienteOut> todosClientes(ClienteFilter clienteFilter, Pageable pageable) {

        Page<Cliente> clientePage = clienteService.allClientePage(clienteFilter, pageable);

        List<ClienteOut> clienteOutList = clienteMapper.toListClienteOut(clientePage.getContent());

        Page<ClienteOut> clientePageOut = new PageImpl<>(clienteOutList,pageable,clientePage.getTotalPages());

        return clientePageOut;

    }

// SEM PAGINACAO
    @GetMapping
    public List<ClienteOut> todosClientes(){
        return clienteMapper.toListClienteOut(clienteService.findAllCliente());
    }

    @GetMapping("/{id}")
    public ClienteOut buscarPorId(@PathVariable Long id){
        return clienteMapper.toClienteOut(clienteService.findId(id));
    }

    @PostMapping
    public ClienteOut salvaCliente(@RequestBody @Valid ClienteInp clienteInp){
        Cliente cliente = clienteMapper.toCliente(clienteInp);
        return clienteMapper.toClienteOut(clienteService.saveCliente(cliente));
    }

    @PutMapping("/{id}")
    public ClienteOut atualizaCliente(@RequestBody @Valid ClienteInp clienteInp, @PathVariable Long id){

        Cliente clienteAtual = clienteService.findId(id);

        Cliente clienteUpdate = clienteMapper.copyToCliente(clienteInp,clienteAtual);

        return clienteMapper.toClienteOut(clienteService.saveCliente(clienteUpdate));

    }

    @DeleteMapping("/{id}")
    //@Operation(summary = "Busca por Codigo")
    public void excluirCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
    }

    @GetMapping("/relatoriocliente")
    public ResponseEntity<InputStreamResource> relatorioClientes() {

        try {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pessoas.pdf");

        InputStreamResource relatorio = new InputStreamResource( clienteService.relatorioCliente());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
