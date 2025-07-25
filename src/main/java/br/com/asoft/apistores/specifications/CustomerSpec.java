package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.CustomerFilter;
import br.com.asoft.apistores.model.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class CustomerSpec {
    public static Specification<Customer> filter(CustomerFilter customerFilter) {
        return (root, query, criteriaBuilder) -> {
            var preditaces = new ArrayList<Predicate>();

            if (customerFilter.getName() != null) {
                preditaces.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+ customerFilter.getName().toLowerCase()+"%"));
            }
            if (customerFilter.getCpfCnpj() != null) {
                preditaces.add(criteriaBuilder.like(root.get("cpfCnpj"),"%"+customerFilter.getCpfCnpj() +"%"));
            }
            if (customerFilter.getEmail() != null) {
                preditaces.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%"+ customerFilter.getEmail().toLowerCase()+"%"));
            }
            return criteriaBuilder.and(preditaces.toArray(new Predicate[0]));
        };
    }
}
