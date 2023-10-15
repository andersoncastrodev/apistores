package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.ClienteInp;
import br.com.asoft.apistores.mapper.ClienteMapper;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.out.ClienteOut;
import br.com.asoft.apistores.service.ClienteService;
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
    public ClienteOut salvaCliente(@RequestBody ClienteInp clienteInp){

        Cliente cliente = clienteMapper.toCliente(clienteInp);

        return clienteMapper.toClienteOut(clienteService.saveCliente(cliente));
    }


}
