package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.StateFilter;
import br.com.asoft.apistores.model.State;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class EstadoSpecification {

    public static Specification<State> filter(StateFilter stateFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<>();

            if(stateFilter.getSigla() != null ) {

                predicates.add(criteriaBuilder.equal(root.get("sigla"), stateFilter.getSigla()));
            }
            if(stateFilter.getNome() != null ) {

                predicates.add(criteriaBuilder.like(root.get("nome"), "%"+ stateFilter.getNome()+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
