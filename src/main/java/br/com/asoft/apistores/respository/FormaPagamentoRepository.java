package br.com.asoft.apistores.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaDePagamento,Long> , JpaSpecificationExecutor<FormaDePagamento> {
}
