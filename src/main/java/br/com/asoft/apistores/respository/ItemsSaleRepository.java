package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.ItemsSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsSaleRepository extends JpaRepository<ItemsSale,Long> {
}
