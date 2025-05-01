package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Users,Long> {
}
