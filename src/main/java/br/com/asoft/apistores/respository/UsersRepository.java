package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    //Optional<Users> findByLogin(String name);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByCpf(String cpf);
}
