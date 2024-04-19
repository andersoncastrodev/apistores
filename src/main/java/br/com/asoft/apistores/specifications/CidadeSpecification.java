package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.CidadeFilter;
import br.com.asoft.apistores.model.Cidade;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class CidadeSpecification {

    public Specification<Cidade> filter(CidadeFilter cidadeFilter) {
        return (root, query, criteriaBuilder) -> {


            var predicates = new ArrayList<>();

            if(cidadeFilter.getNome() != null ) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),"%"+cidadeFilter.getNome()+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
