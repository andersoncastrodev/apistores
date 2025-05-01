package br.com.asoft.apistores.respository;

import br.com.asoft.apistores.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long>, JpaSpecificationExecutor<City> {
}
