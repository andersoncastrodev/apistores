package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.FornecedorInp;
import br.com.asoft.apistores.mapper.FornecedorMapper;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.out.FornecedorOut;
import br.com.asoft.apistores.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public FornecedorOut buscaPorId(@PathVariable Long id) {
        FornecedorOut fornecedorOut = fornecedorMapper.toFornecedorOut(fornecedorService.findId(id));
        return fornecedorOut;
    }

    @PostMapping
    public FornecedorOut salvarFornecedor(@RequestBody @Valid FornecedorInp fornecedorInp){
        return fornecedorMapper.toFornecedorOut( fornecedorService.saveFonecedor(fornecedorMapper.toFornecedor(fornecedorInp)));
    }

}
