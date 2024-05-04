package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.ClienteFilter;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.model.Pessoa;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class ClienteSpecification {

    public static Specification<Cliente> filter(ClienteFilter clienteFilter) {
        return (root, query, criteriaBuilder) -> {

            var preditaces = new ArrayList<Predicate>();

            // Fazendo o Join como a Entidade(Pessoa).
            // pessoa = É o nome de um atributo na Entidade(Cliente)
            Join<Cliente,Pessoa> clientePessoaJoin = root.join("pessoa");

            if ( clienteFilter.getNome() != null) {
                // nome = É o nome de um atributo da Entidade(Pessoa)
                preditaces.add(criteriaBuilder.like(clientePessoaJoin.get("nome"), "%"+clienteFilter.getNome()+"%") );

            }
            if (clienteFilter.getTelefone() != null){
                // telefone = É o nome de um atributo da Entidade(Pessoa)
                preditaces.add(criteriaBuilder.equal(clientePessoaJoin.get("telefone"), clienteFilter.getTelefone()));
            }

            return criteriaBuilder.and(preditaces.toArray(new Predicate[0]));

        };

    }
}
