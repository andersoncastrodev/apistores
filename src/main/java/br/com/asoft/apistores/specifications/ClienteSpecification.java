package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.ClienteFilter;
import br.com.asoft.apistores.model.Cliente;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class ClienteSpecification {

    public static Specification<Cliente> filter(ClienteFilter clienteFilter) {
        return (root, query, criteriaBuilder) -> {

            var preditaces = new ArrayList<>();

            // Adicionando o critério de Like para o nome da pessoa
            if (clienteFilter.getPessoa() != null && clienteFilter.getPessoa().getNome() != null) {

                // Fazendo a junção com a entidade Pessoa
                root.join("pessoa");

                preditaces.add(criteriaBuilder.like(root.get("pessoa").get(("nome")), "%"+clienteFilter.getPessoa().getNome()+"%"));
            }

            return criteriaBuilder.and(preditaces.toArray(new Predicate[0]));
        };
    }
}
