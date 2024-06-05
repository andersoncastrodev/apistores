package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.FornecedorFilter;
import br.com.asoft.apistores.model.Fornecedor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.function.Predicate;

public class FornecedorSpecification {

    public static Specification<Fornecedor> filter(FornecedorFilter fornecedorFilter) {

        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<>();

            //if
            return null;
        };

    }
}
