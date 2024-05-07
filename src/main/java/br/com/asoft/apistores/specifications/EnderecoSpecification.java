package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.EnderecoFilter;
import br.com.asoft.apistores.model.Endereco;
import org.springframework.data.jpa.domain.Specification;

public class EnderecoSpecification {

    public static Specification<Endereco> filter(EnderecoFilter enderecoFilter) {
        return (root, query, criteriaBuilder) -> {


          return null;
        };
    }
}
