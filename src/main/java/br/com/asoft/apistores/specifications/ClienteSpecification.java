package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.ClientFilter;
import br.com.asoft.apistores.model.Customer;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {

    public static Specification<Customer> filter(ClientFilter clientFilter) {
//        return (root, query, criteriaBuilder) -> {
//
//            var preditaces = new ArrayList<Predicate>();
//
//            // Fazendo o Join como a Entidade(Pessoa).
//            // pessoa = É o nome de um atributo na Entidade(Client)
//            Join<Client,Pessoa> clientePessoaJoin = root.join("pessoa");
//
//            if ( clientFilter.getNome() != null) {
//                // nome = É o nome de um atributo da Entidade(Pessoa)
//                preditaces.add(criteriaBuilder.like(clientePessoaJoin.get("nome"),"%"+ clientFilter.getNome()+"%") );
//
//            }
//            if (clientFilter.getTelefone() != null) {
//                // telefone = É o nome de um atributo da Entidade(Pessoa)
//                preditaces.add(criteriaBuilder.equal(clientePessoaJoin.get("telefone"), clientFilter.getTelefone()));
//            }
//
//            return criteriaBuilder.and(preditaces.toArray(new Predicate[0]));
//
//        };
        return null;
    }

}
