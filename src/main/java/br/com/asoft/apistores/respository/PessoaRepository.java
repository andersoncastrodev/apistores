package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.dtointerface.PessoaNome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

   // List<Pessoa> findAllByOrderByIdDesc();
    List<Pessoa> findAllByNomeOrderByIdAsc(String name);

    List<PessoaNome> findAllByOrderByIdDesc();

    //Para saber se exist uma Pessoa com esse nome.
    boolean existsByNome(String nome);


}
