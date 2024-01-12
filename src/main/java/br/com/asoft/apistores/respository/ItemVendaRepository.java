package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda,Long> {

   //
}
