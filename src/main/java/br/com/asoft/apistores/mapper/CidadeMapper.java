package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.CidadeInp;
import br.com.asoft.apistores.model.City;
import br.com.asoft.apistores.out.CidadeOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CidadeMapper {

    CidadeOut toCidadeOut(City city);

    City toCidade(CidadeInp cidadeInp);

    City copyToCidade(CidadeInp cidadeInp, @MappingTarget City city);

    List<CidadeOut> toListCidadeOut(List<City> cities);

}
