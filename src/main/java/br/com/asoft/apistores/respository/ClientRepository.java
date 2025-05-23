package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>, JpaSpecificationExecutor<Client> {
}
