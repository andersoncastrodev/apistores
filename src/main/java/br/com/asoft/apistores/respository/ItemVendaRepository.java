package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemSale,Long> {
}
