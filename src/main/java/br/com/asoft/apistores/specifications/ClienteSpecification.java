package br.com.asoft.apistores.specifications;

import br.com.asoft.apistores.filter.ClienteFilter;
import br.com.asoft.apistores.model.Cliente;
import br.com.asoft.apistores.model.Pessoa;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class ClienteSpecification {

    public static Specification<Cliente> filter(ClienteFilter clienteFilter) {
        return (root, query, criteriaBuilder) -> {

            var preditaces = new ArrayList<Predicate>();

            Join<Cliente,Pessoa> clientePessoaJoin = root.join("pessoa");

            // Adicionando o critério de Like para o nome da pessoa
            if (clienteFilter.getPessoa() != null && clienteFilter.getPessoa().getNome() != null) {

                // Fazendo a junção com a entidade Pessoa
                root.join("pessoa");

                preditaces.add(criteriaBuilder.like(clientePessoaJoin.get("nome"), "%"+clienteFilter.getPessoa().getNome()+"%"));

              //  preditaces.add(criteriaBuilder.like(root.get("pessoa").get("nome"), "%"+clienteFilter.getPessoa().getNome()+"%"));
            }

            return criteriaBuilder.and(preditaces.toArray(new Predicate[0]));

//            Predicate predicate = criteriaBuilder.conjunction();
//
//            if (clienteFilter.getId() != null) {
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), clienteFilter.getId()));
//            }
//
//            if (clienteFilter.getTipo() != null) {
//                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("tipo"), clienteFilter.getTipo()));
//            }
//
//            if (clienteFilter.getPessoa() != null) {
//                Pessoa pessoa = clienteFilter.getPessoa();
//                Join<Cliente, Pessoa> pessoaJoin = root.join("pessoa");
//
//                if (pessoa.getId() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(pessoaJoin.get("id"), pessoa.getId()));
//                }
//
//                if (pessoa.getNome() != null) {
//                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(pessoaJoin.get("nome"), pessoa.getNome()));
//                }
//
//                // Adicione mais condições conforme necessário para outros campos da entidade Pessoa
//
//            }
//
//            return predicate;
        };
    }
}
