package br.com.asoft.apistores.service;


import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.ItenVenda;
import br.com.asoft.apistores.respository.ItenVendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemVendaService {

    private final ItenVendaRepository itenVendaRepository;

    public List<ItenVenda> allTodos(){
       return itenVendaRepository.findAll();
    }

    public ItenVenda findId(Long id){
        return tryOrFaill(id);
    }


    public ItenVenda tryOrFaill(Long id){
        return itenVendaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("ItenVenda",id));
    }

}
