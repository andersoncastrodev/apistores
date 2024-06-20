package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.FormaPagamentoMapper;
import br.com.asoft.apistores.out.FormaPagamentoOut;
import br.com.asoft.apistores.service.FormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formapagamento")
@RequiredArgsConstructor
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    private final FormaPagamentoMapper formaDePagamentoMapper;


    @GetMapping
   public List<FormaPagamentoOut> findAllFormagamento(){

       List<FormaPagamentoOut>  formaPagamentoOuts ;

       return  null;
   }


}
