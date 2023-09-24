package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.respository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> allTodos(){
        return produtoRepository.findAll();
    }

    public Produto findId(Long id){
        return tryOrFaill(id);
    }

    public Produto tryOrFaill(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Produto",id));
    }

}
