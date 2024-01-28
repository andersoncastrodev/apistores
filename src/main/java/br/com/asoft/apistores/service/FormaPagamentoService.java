package br.com.asoft.apistores.service;

import br.com.asoft.apistores.respository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormaPagamentoService {


    private final FormaPagamentoRepository formaPagamentoRepository;



}
