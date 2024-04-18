package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.CidadeFilter;
import br.com.asoft.apistores.model.Cidade;
import org.springframework.data.jpa.domain.Specification;

public class CidadeSpecification {

    public Specification<Cidade> filter(CidadeFilter cidadeFilter) {
        return (root, query, criteriaBuilder) -> {






            return null;
        };
    }
}
