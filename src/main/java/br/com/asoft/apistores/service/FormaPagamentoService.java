package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.model.FormaDePagamento;
import br.com.asoft.apistores.respository.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaDePagamento> findAll(){
        return formaPagamentoRepository.findAll();
    }

    public FormaDePagamento findId(Long id){
        return tryOrFail(id);
    }

    public FormaDePagamento tryOrFail(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Forma de Pagamento",id));
    }




}
