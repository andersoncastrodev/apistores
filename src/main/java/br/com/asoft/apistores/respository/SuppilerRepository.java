package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppilerRepository extends JpaRepository<Supplier,Long>, JpaSpecificationExecutor<Supplier> {


}
