package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.inp.ProdutoInp;
import br.com.asoft.apistores.mapper.ProductMapper;
import br.com.asoft.apistores.model.Product;
import br.com.asoft.apistores.out.ProdutoOut;
import br.com.asoft.apistores.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping
    public Page<ProdutoOut> buscaTodos(Pageable pageable) {

        Page<Product> produtoPage = productService.allTodosPage(pageable);

        List<ProdutoOut> produtoOutsList = productMapper.toListProdutoOut(produtoPage.getContent());

        Page<ProdutoOut> produtoOutPage = new PageImpl<>(produtoOutsList,pageable,produtoPage.getTotalElements());

        return produtoOutPage;
    }

//    @GetMapping
//    public List<ProdutoOut> buscaTodos(){
//        List<ProdutoOut> produtos = productMapper.toListProdutoOut(productService.allTodos());
//        return produtos;
//    }

    @GetMapping("/{id}")
    public ProdutoOut buscaPorId(@PathVariable Long id){
        ProdutoOut produtoOut = productMapper.toProdutoOut(productService.findId(id));
        return produtoOut;
    }

    @PostMapping
    public ProdutoOut salvarProduto(@RequestBody @Valid ProdutoInp produtoInp){

        Product product = productMapper.toProduto(produtoInp);

        ProdutoOut produtoOut = productMapper.toProdutoOut(productService.salvaProduto(product));

        return produtoOut;
    }

    @PutMapping("/{id}")
    public ProdutoOut alteraProduto(@RequestBody ProdutoInp produtoInp, @PathVariable Long id){

        Product productAtual = productService.findId(id);

        Product productNovo = productMapper.copyToProduto(produtoInp, productAtual);

        return productMapper.toProdutoOut(productService.salvaProduto(productNovo));

    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id){

        productService.deletarProduto(id);
    }

    @GetMapping("/relatorioprodutos")
    public ResponseEntity<InputStreamResource> relatorioPessoas() {

        try {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pessoas.pdf");

        InputStreamResource relatorio = new InputStreamResource(productService.relatorioTodosProdutos());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(relatorio);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
