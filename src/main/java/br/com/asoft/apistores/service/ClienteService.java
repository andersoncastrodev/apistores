package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.respository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final PessoaService pessoaService;

    public List<Cliente> findAllCliente(){
        return clienteRepository.findAll();
    }

    public Cliente findId(Long id){
        return tryOrFail(id);
    }

    public Cliente saveCliente(Cliente cliente){

        Pessoa pessoa = pessoaService.findId(cliente.getPessoa().getId());

        cliente.setPessoa(pessoa);

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id){

        Cliente cliente = findId(id);

        clienteRepository.delete(cliente);

        clienteRepository.flush();
    }

    public Cliente tryOrFail(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Cliente",id));
    }
}
