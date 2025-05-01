package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Address, Long>,
        JpaSpecificationExecutor<Address> {
}
