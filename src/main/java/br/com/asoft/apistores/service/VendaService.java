package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Venda;
import br.com.asoft.apistores.respository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;

    public List<Venda> allTodas(){
        return vendaRepository.findAll();
    }

    public Venda findId(Long id){
        return tryOrFail(id);
    }

    public Venda tryOrFail(Long id){

        return vendaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Venda",id));
    }
}
