package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.respository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public List<Fornecedor> allTodos(){
        return fornecedorRepository.findAll();
    }

    public Fornecedor findId(Long id){
        return tryOrFail(id);
    }

    public Fornecedor saveFonecedor(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor tryOrFail(Long id){
        return fornecedorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Fornecedor", id));
    }
}
