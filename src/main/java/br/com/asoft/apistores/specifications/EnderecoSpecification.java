package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.EnderecoFilter;
import br.com.asoft.apistores.model.Endereco;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class EnderecoSpecification {

    public static Specification<Endereco> filter(EnderecoFilter enderecoFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            if (enderecoFilter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), enderecoFilter.getId()));
            }

            if (enderecoFilter.getRua() != null) {
                predicates.add(criteriaBuilder.equal(root.get("rua"), enderecoFilter.getRua()));
            }
            if (enderecoFilter.getCep() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("cep"), enderecoFilter.getCep()));
            }
            if (enderecoFilter.getNomecidade() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nome"), enderecoFilter.getNomecidade()));
            }
            if (enderecoFilter.getNomeestado() != null) {
                predicates.add(criteriaBuilder.equal(root.get("nome"), enderecoFilter.getNomeestado()));
            }
            if (enderecoFilter.getSigla() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("sigla"), enderecoFilter.getSigla()));
            }

          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
