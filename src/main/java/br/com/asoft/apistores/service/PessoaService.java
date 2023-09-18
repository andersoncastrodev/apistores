package br.com.asoft.apistores.service;

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
}