package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.out.FormaPagamentoOut;
import br.com.asoft.apistores.service.FormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formapagamento")
@RequiredArgsConstructor
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;


   public List<FormaPagamentoOut> findAllFormagamento(){

       formaPagamentoService.findAll();

       return  null;
   }


}
