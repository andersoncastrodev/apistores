package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.out.ItenVendaOut;
import br.com.asoft.apistores.service.ItenVendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itenvendas")
@RequiredArgsConstructor
public class ItenVendaController{

    private final ItenVendaService itenVendaService;
    public List<ItenVendaOut> todasItenVendas(){

        return null;
    }
}
