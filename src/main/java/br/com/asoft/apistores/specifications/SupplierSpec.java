package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.SupplierFilter;
import br.com.asoft.apistores.model.Supplier;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class SupplierSpec {

    public static Specification<Supplier> filter(SupplierFilter supplierFilter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<>();

            if (supplierFilter.getName() != null ) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+supplierFilter.getName().toLowerCase()+"%"));
            }
            if (supplierFilter.getCpfCnpj() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("cpfCnpj")),"%"+supplierFilter.getCpfCnpj().toLowerCase()+"%"));
            }
            if (supplierFilter.getEmail() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),"%"+supplierFilter.getEmail().toLowerCase()+"%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
