package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.out.FormaPagamentoOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormaPagamentoMapper {

    FormaPagamentoOut toFormaPagamentoOut(FormaDePagamento formaDePagamento);
    FormaDePagamento toFormaPagamento(FormaPagamentoInp formaPagamentoInp);
    FormaDePagamento copyToFormaPagamento(FormaPagamentoInp formaPagamentoInp, @MappingTarget FormaDePagamento formaDePagamento);
    List<FormaPagamentoOut> toListFormaPagamentoOut(List<FormaDePagamento> formaDePagamentos);

}
