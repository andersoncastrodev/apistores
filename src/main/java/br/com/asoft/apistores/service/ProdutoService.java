package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.respository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final FornecedorService fornecedorService;

    public List<Produto> allTodos(){
        return produtoRepository.findAll();
    }

    public Produto findId(Long id){
        return tryOrFail(id);
    }

    public Produto salvaProduto(Produto produto){

        Long fornecedorId = produto.getFornecedor().getId();

        Fornecedor fornecedor = fornecedorService.tryOrFail(fornecedorId);

        produto.setFornecedor(fornecedor);

        return produtoRepository.save(produto);
    }

    public Produto tryOrFail(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Produto",id));
    }



}
