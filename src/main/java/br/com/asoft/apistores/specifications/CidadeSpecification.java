package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.CityFilter;
import br.com.asoft.apistores.model.City;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class CidadeSpecification {

    public static Specification<City> filter(CityFilter cityFilter) {
        return (root, query, criteriaBuilder) -> {

            ArrayList<Object> predicates = new ArrayList<>();

            if(cityFilter.getNome() != null ) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),"%"+ cityFilter.getNome()+"%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
