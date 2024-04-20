package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long>, JpaSpecificationExecutor<Cidade> {
}
