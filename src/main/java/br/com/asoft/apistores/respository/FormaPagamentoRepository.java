package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaDePagamento,Long> {
}
