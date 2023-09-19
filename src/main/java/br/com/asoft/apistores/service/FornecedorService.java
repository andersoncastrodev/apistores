package br.com.asoft.apistores.service;

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
}
