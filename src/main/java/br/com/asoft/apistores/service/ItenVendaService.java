package br.com.asoft.apistores.service;

import br.com.asoft.apistores.respository.ItenVenda;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItenVendaService {

    private final ItenVenda itenVenda;

    public List<ItenVenda> findAll(){
       return itenVenda.findAll();
    }

}
