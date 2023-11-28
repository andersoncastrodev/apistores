package br.com.asoft.apistores.service;


import br.com.asoft.apistores.model.ItenVenda;
import br.com.asoft.apistores.respository.ItenVendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItenVendaService {

    private final ItenVendaRepository itenVendaRepository;

    public List<ItenVenda> allTodos(){
       return itenVendaRepository.findAll();
    }

}
