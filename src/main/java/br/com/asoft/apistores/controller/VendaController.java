package br.com.asoft.apistores.controller;


import br.com.asoft.apistores.mapper.VendaMapper;
import br.com.asoft.apistores.out.VendaOut;
import br.com.asoft.apistores.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
