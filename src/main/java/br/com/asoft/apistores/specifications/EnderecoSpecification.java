package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.AddressFilter;
import br.com.asoft.apistores.model.Address;
import br.com.asoft.apistores.model.City;
import br.com.asoft.apistores.model.State;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;

public class EnderecoSpecification {

    public static Specification<Address> filter(AddressFilter addressFilter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            //Fazendo o JOIN Nessarios
            Join<Address, City> enderecoCidadeJoin = root.join("cidade");

            //OBS: pega o ultimo join e FAZ outro JOIN.
            Join<City, State> cidadeEstadoJoin = enderecoCidadeJoin.join("estado");

            if (addressFilter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), addressFilter.getId()));
            }

            if (addressFilter.getRua() != null) {
                predicates.add(criteriaBuilder.equal(root.get("rua"), addressFilter.getRua()));
            }
            if (addressFilter.getCep() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("cep"), addressFilter.getCep()));
            }
            if (addressFilter.getNomecidade() != null) {
                predicates.add(criteriaBuilder.equal( enderecoCidadeJoin.get("nome"), addressFilter.getNomecidade()));
            }
            if (addressFilter.getNomeestado() != null) {
                //predicates.add(criteriaBuilder.equal(root.get("nome"), addressFilter.getNomeestado()));
                predicates.add(criteriaBuilder.equal(cidadeEstadoJoin.get("nome"), addressFilter.getNomeestado()));
            }
            if (addressFilter.getSigla() != null ) {
              //  predicates.add(criteriaBuilder.equal(root.get("sigla"), addressFilter.getSigla()));
                predicates.add(criteriaBuilder.equal(cidadeEstadoJoin.get("sigla"), addressFilter.getSigla()));
            }

          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
