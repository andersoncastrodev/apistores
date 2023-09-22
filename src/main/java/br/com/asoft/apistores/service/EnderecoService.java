package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.respository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public List<Endereco> allEndereco(){
        return enderecoRepository.findAll();
    }

    public Endereco findId(Long id){
        return tryOrFaill(id);
    }

    public Endereco tryOrFaill(Long id){
        return enderecoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Endereco",id));
    }
}
