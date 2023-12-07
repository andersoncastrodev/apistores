package br.com.asoft.apistores.service;


import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.ItemVenda;
import br.com.asoft.apistores.model.Venda;
import br.com.asoft.apistores.respository.ItemVendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;

    private final VendaService vendaService;

    public List<ItemVenda> allTodos(){
       return itemVendaRepository.findAll();
    }

    public ItemVenda findId(Long id){
        return tryOrFaill(id);
    }

    public ItemVenda saveItemVenda(ItemVenda itenVenda){

        Long vendaId = itenVenda.getVenda().getId();

        Venda venda = vendaService.tryOrFail(vendaId);

        itenVenda.setVenda(venda);

        return itemVendaRepository.save(itenVenda);
    }

    public ItemVenda tryOrFaill(Long id){
        return itemVendaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("ItenVenda",id));
    }

}
