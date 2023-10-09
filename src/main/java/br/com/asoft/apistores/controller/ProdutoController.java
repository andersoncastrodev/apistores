package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.ProdutoInp;
import br.com.asoft.apistores.mapper.ProdutoMapper;
import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.out.ProdutoOut;
import br.com.asoft.apistores.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ProdutoOut salvarProduto(@RequestBody @Valid ProdutoInp produtoInp){

        Produto produto = produtoMapper.toProduto(produtoInp);

        ProdutoOut produtoOut = produtoMapper.toProdutoOut(produtoService.salvaProduto(produto));

        return produtoOut;
    }

    @PutMapping("/{id}")
    public ProdutoOut alteraProduto(@RequestBody ProdutoInp produtoInp, @PathVariable Long id){

        Produto produtoAtual = produtoService.findId(id);

        Produto produtoNovo = produtoMapper.copyToProduto(produtoInp,produtoAtual);

        return produtoMapper.toProdutoOut(produtoService.salvaProduto(produtoNovo));

    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id){

        produtoService.deletarProduto(id);
    }




}
