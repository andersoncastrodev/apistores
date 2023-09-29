package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Pessoa;
import br.com.asoft.apistores.respository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<Pessoa> allPessoas(){
        return pessoaRepository.findAll();
    }

    public Pessoa findId(Long id){
        return tryOrFail(id);
    }

    public Pessoa tryOrFail(Long id){
        return pessoaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Pessoa",id));
    }

    public Pessoa savePessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
}
