package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.model.Fornecedor;
import org.springframework.data.jpa.domain.Specification;

public class FornecedorSpecification {

    public static Specification<Fornecedor> filter() {

        return (root, query, criteriaBuilder) -> {

            return null;
        };

    }
}
