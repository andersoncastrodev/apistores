package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.filter.CityFilter;
import br.com.asoft.apistores.model.City;
import br.com.asoft.apistores.respository.CityRepository;
import br.com.asoft.apistores.specifications.CidadeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

//    public Page<City> allCidadesPage(CityFilter cityFilter, Pageable pageable){
//        return cityRepository.findAll(CidadeSpecification.filter(cityFilter), pageable);
//    }

    public City findId(Long id){
        return tryOrFail(id);
    }

    public City tryOrFail(Long id){
        return cityRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("City",id));
    }

    public City saveCity(City city){
        return cityRepository.save(city);
    }

//    public void deletarCity(City city) {
//        cityRepository.delete(city);
//        cityRepository.flush();
//    }


}
