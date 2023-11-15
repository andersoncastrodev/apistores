package br.com.asoft.apistores.controller;


import br.com.asoft.apistores.inp.VendaInp;
import br.com.asoft.apistores.mapper.VendaMapper;
import br.com.asoft.apistores.model.Venda;
import br.com.asoft.apistores.out.VendaOut;
import br.com.asoft.apistores.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    private final VendaMapper vendaMapper;

    @GetMapping
    public List<VendaOut> listaVendas(){
        return  vendaMapper.toListVendaOut( vendaService.allTodas());
    }

    @GetMapping("/{id}")
    public VendaOut buscaPorId(@PathVariable Long id){
        return vendaMapper.toVendaOut( vendaService.findId(id));
    }

    @PostMapping
    public VendaOut salvaVenda(@RequestBody @Valid VendaInp vendaInp){

        Venda venda = vendaService.saveVenda(vendaMapper.toVenda(vendaInp));
       return vendaMapper.toVendaOut(venda);
    }

    @DeleteMapping("/{id}")
    public void excluirVenda(@PathVariable Long id){
            vendaService.deleteVenda(id);
    }

}
