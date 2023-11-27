package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.out.ItenVendaOut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itenvendas")
public class ItenVendaController{

    public List<ItenVendaOut> todasItenVendas(){


    }
}
