package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.FornecedorMapper;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.out.FornecedorOut;
import br.com.asoft.apistores.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    private final FornecedorMapper fornecedorMapper;
    @GetMapping
    public List<FornecedorOut> buscaTodos(){
        List<FornecedorOut> fornecedores = fornecedorMapper.toListFornecedorOut(fornecedorService.allTodos());
        return fornecedores;
    }
}
