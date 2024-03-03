package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    List<Pessoa> findByNomeOrderByIdDesc();
}
