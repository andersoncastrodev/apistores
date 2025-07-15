package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem,Long> {
}
