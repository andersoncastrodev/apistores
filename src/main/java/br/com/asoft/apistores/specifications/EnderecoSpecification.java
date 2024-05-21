package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.EnderecoFilter;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.model.Estado;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class EnderecoSpecification {

    public static Specification<Endereco> filter(EnderecoFilter enderecoFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            //Fazendo o JOIN Nessarios
            Join<Endereco, Cidade> enderecoCidadeJoin = root.join("cidade");

            //OBS: pega o ultimo join e FAZ outro JOIN.
            Join<Cidade, Estado> cidadeEstadoJoin = enderecoCidadeJoin.join("estado");

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
                predicates.add(criteriaBuilder.equal( enderecoCidadeJoin.get("nome"), enderecoFilter.getNomecidade()));
            }
            if (enderecoFilter.getNomeestado() != null) {
                //predicates.add(criteriaBuilder.equal(root.get("nome"), enderecoFilter.getNomeestado()));
                predicates.add(criteriaBuilder.equal(cidadeEstadoJoin.get("nome"), enderecoFilter.getNomeestado()));
            }
            if (enderecoFilter.getSigla() != null ) {
              //  predicates.add(criteriaBuilder.equal(root.get("sigla"), enderecoFilter.getSigla()));
                predicates.add(criteriaBuilder.equal(cidadeEstadoJoin.get("sigla"), enderecoFilter.getSigla()));
            }

          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
