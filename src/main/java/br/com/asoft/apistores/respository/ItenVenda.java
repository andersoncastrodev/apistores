package br.com.asoft.apistores.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItenVenda extends JpaRepository<ItenVenda,Long> {
}
