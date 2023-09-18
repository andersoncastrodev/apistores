package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
