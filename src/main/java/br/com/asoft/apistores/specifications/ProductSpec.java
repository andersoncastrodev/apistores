package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.ProductFilter;
import br.com.asoft.apistores.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class ProductSpec {

    public static Specification<Product> filter(ProductFilter productFilter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (productFilter.getDescription() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + productFilter.getDescription().toLowerCase() + "%"));
            }
            if (productFilter.getBarcode() != null) {
                predicates.add(criteriaBuilder.equal(root.get("barcode"), productFilter.getBarcode().toLowerCase()));
            }
//            if (productFilter.getCategory() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("category"), productFilter.getCategory()));
//            }
//            if (productFilter.getSupplier() != null) {
//                predicates.add(criteriaBuilder.equal(root.get("supplier"), productFilter.getSupplier()));
//            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

