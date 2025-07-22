package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
