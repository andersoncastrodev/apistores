package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.ClienteMapper;
import br.com.asoft.apistores.out.ClienteOut;
import br.com.asoft.apistores.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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






}
