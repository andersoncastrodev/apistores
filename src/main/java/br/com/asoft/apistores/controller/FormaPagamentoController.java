package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.filter.FormaPagamentoFilter;
import br.com.asoft.apistores.mapper.FormaPagamentoMapper;
import br.com.asoft.apistores.model.FormaDePagamento;
import br.com.asoft.apistores.out.FormaPagamentoOut;
import br.com.asoft.apistores.service.FormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Page<FormaPagamentoOut> findAllFormagamento(FormaPagamentoFilter formaPagamentoFilter, Pageable pageable) {

        Page<FormaDePagamento> formaPagamentoPage = formaPagamentoService.findAll(formaPagamentoFilter, pageable);

        List<FormaPagamentoOut> listFormaPagamentoOutList = formaDePagamentoMapper.toListFormaPagamentoOut(formaPagamentoPage.getContent());

        Page<FormaPagamentoOut> formaPagamentoOut = new PageImpl<>(listFormaPagamentoOutList,pageable,formaPagamentoPage.getTotalPages());

        return formaPagamentoOut;
    }

    @GetMapping("/{id}")
    public FormaPagamentoOut findById(@PathVariable Long id){

        return null;
    }



}
