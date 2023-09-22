package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.respository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> allCidades(){
        return cidadeRepository.findAll();
    }

    public Cidade findId(Long id){
        return tryOrFaill(id);
    }

    public Cidade tryOrFaill(Long id){
        return cidadeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Cidade",id));
    }

}
