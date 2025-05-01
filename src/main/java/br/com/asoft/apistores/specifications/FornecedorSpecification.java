package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.SupplierFilter;
import br.com.asoft.apistores.model.Supplier;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class FornecedorSpecification {

    public static Specification<Supplier> filter(SupplierFilter supplierFilter) {

        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<>();

            if (supplierFilter.getNome() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("nome"), supplierFilter.getNome()));
            }
            if (supplierFilter.getSigla() != null) {
                predicates.add(criteriaBuilder.equal(root.get("sigla"), supplierFilter.getNome()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
