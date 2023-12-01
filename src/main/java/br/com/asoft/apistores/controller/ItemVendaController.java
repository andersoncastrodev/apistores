package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.ItemVendaInp;
import br.com.asoft.apistores.mapper.ItemVendaMapper;
import br.com.asoft.apistores.out.ItemVendaOut;
import br.com.asoft.apistores.service.ItemVendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itenvendas")
@RequiredArgsConstructor
public class ItemVendaController {

    private final ItemVendaService itenVendaService;

    private final ItemVendaMapper itenVendaMapper;

    @GetMapping
    public List<ItemVendaOut> todasItenVendas(){
        return itenVendaMapper.toListItemVendaOut(itenVendaService.allTodos());
    }

    @GetMapping("/{id}")
    public ItemVendaOut buscaIdVenda(@PathVariable Long id){
        return itenVendaMapper.toItenVendaOut(itenVendaService.findId(id));
    }

    @PostMapping
    public ItemVendaOut salvaItemVenda(@RequestBody ItemVendaInp itenVendaInp){


    }


}
