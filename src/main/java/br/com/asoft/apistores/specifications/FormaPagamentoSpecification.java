package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.FormaPagamentoFilter;
import br.com.asoft.apistores.model.FormaDePagamento;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;


public class FormaPagamentoSpecification {

    public static Specification<FormaDePagamento> filter(FormaPagamentoFilter formaPagamentoFilter){

        return(root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<>();

            if( formaPagamentoFilter.getId() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("id"), formaPagamentoFilter.getId()));
            }
            if (formaPagamentoFilter.getDescricao() != null ){
                predicates.add(criteriaBuilder.equal(root.get("descricao"), formaPagamentoFilter.getDescricao()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
