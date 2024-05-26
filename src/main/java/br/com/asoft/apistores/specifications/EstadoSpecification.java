package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.EstadoFilter;
import br.com.asoft.apistores.model.Estado;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.function.Predicate;

public class EstadoSpecification {

    public static Specification<Estado> filter(EstadoFilter estadoFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<>();

            if(estadoFilter.getSigla() != null ) {

                predicates.add(criteriaBuilder.equal(root.get("sigla"), estadoFilter.getSigla()));
            }
            if(estadoFilter.getNome() != null ) {

                predicates.add(criteriaBuilder.like(root.get("nome"), "%"+ estadoFilter.getNome()+"%"));
            }



            return null;
        };
    }
}
