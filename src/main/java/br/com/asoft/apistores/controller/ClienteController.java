package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.ClienteInp;
import br.com.asoft.apistores.mapper.ClienteMapper;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.out.ClienteOut;
import br.com.asoft.apistores.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteMapper clienteMapper;

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




}
