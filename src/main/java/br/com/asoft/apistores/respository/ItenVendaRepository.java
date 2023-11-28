package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.ItenVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItenVendaRepository extends JpaRepository<ItenVenda,Long> {
}
