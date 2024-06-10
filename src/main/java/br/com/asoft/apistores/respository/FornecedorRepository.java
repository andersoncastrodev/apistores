package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long>
        , JpaSpecificationExecutor<Fornecedor> {
}
