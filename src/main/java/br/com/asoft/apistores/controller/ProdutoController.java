package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.mapper.ProdutoMapper;
import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.out.ProdutoOut;
import br.com.asoft.apistores.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    private final ProdutoMapper produtoMapper;

    @GetMapping
    public List<ProdutoOut> buscaTodos(){
        List<ProdutoOut> produtos = produtoMapper.toListProdutoOut(produtoService.allTodos());
        return produtos;
    }

    @GetMapping("/{id}")
    public ProdutoOut buscaPorId(@PathVariable Long id){
        ProdutoOut produtoOut = produtoMapper.toProdutoOut(produtoService.findId(id));
        return produtoOut;
    }


}
