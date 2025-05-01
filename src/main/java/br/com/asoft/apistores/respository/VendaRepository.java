package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Sales,Long> {
}
