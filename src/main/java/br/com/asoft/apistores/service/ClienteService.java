package br.com.asoft.apistores.service;

import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.respository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAllCliente(){
        return clienteRepository.findAll();
    }



}
