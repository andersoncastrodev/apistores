package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public List<Produto> buscaTodos(){
        return produtoService.allTodos();
    }


}
