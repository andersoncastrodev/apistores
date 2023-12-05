package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.FornecedorInp;
import br.com.asoft.apistores.inp.ItemVendaInp;
import br.com.asoft.apistores.mapper.ItemVendaMapper;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.model.ItemVenda;
import br.com.asoft.apistores.out.ItemVendaOut;
import br.com.asoft.apistores.service.ItemVendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itenvendas")
@RequiredArgsConstructor
public class ItemVendaController {

    private final ItemVendaService itemVendaService;

    private final ItemVendaMapper itemVendaMapper;

    @GetMapping
    public List<ItemVendaOut> todasItenVendas(){
        return itemVendaMapper.toListItemVendaOut(itemVendaService.allTodos());
    }

    @GetMapping("/{id}")
    public ItemVendaOut buscaIdVenda(@PathVariable Long id){
        return itemVendaMapper.toItenVendaOut(itemVendaService.findId(id));
    }

    @PostMapping
    public ItemVendaOut salvarItemVenda(@RequestBody ItemVendaInp itenVendaInp){
        ItemVenda itemVenda = itemVendaService.saveItemVenda( itemVendaMapper.toItenVenda(itenVendaInp));
        return itemVendaMapper.toItenVendaOut(itemVenda);

    }

    @PutMapping("/{id}")
    public ItemVendaOut alterarFornecedor(@RequestBody @Valid FornecedorInp fornecedorInp, @PathVariable Long id){

        ItemVenda itemVendaAtual = itemVendaService.findId(id);

        ItemVenda itemVendaNovo = itemVendaMapper.copyToFornecedor(fornecedorInp, fornecedorAtual);

        return fornecedorMapper.toFornecedorOut(fornecedorService.saveFonecedor(fornecedorNovo));
    }



}
