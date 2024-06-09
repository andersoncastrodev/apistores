package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.FornecedorFilter;
import br.com.asoft.apistores.model.Fornecedor;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class FornecedorSpecification {

    public static Specification<Fornecedor> filter(FornecedorFilter fornecedorFilter) {

        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<>();

            if (fornecedorFilter.getNome() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("nome"), fornecedorFilter.getNome()));
            }
            if (fornecedorFilter.getSigla() != null) {
                predicates.add(criteriaBuilder.equal(root.get("sigla"), fornecedorFilter.getNome()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
