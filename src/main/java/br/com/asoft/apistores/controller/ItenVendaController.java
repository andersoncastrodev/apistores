package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.ItemVendaMapper;
import br.com.asoft.apistores.out.ItenVendaOut;
import br.com.asoft.apistores.service.ItenVendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itenvendas")
@RequiredArgsConstructor
public class ItenVendaController{

    private final ItenVendaService itenVendaService;

    private final ItemVendaMapper itenVendaMapper;

    @GetMapping
    public List<ItenVendaOut> todasItenVendas(){

        return itenVendaMapper.toListItemVendaOut(itenVendaService.allTodos());
    }
}
