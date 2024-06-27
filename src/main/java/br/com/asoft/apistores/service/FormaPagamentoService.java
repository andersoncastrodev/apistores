package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.FormaPagamentoFilter;
import br.com.asoft.apistores.model.FormaDePagamento;
import br.com.asoft.apistores.respository.FormaPagamentoRepository;
import br.com.asoft.apistores.specifications.FormaPagamentoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public Page<FormaDePagamento> findAll(FormaPagamentoFilter formaPagamentoFilter, Pageable pageable){

        return formaPagamentoRepository.findAll(FormaPagamentoSpecification.filter(formaPagamentoFilter), pageable);
    }

    public FormaDePagamento findId(Long id){
        return tryOrFail(id);
    }

    public FormaDePagamento tryOrFail(Long id){
        return formaPagamentoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Forma de Pagamento",id));
    }




}
