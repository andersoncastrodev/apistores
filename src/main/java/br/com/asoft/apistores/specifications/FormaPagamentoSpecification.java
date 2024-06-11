package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.model.FormaDePagamento;
import org.springframework.data.jpa.domain.Specification;

public class FormaPagamentoSpecification {

    public static Specification<FormaDePagamento> filter(){

        return(root, query, criteriaBuilder) -> {


            return null;
        };
    }
}
