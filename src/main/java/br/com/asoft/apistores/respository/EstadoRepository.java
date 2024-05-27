package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long>, JpaSpecificationExecutor<Estado> {
}
