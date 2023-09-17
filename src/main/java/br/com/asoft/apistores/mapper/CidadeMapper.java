package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.out.CidadeOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CidadeMapper {

    CidadeOut toCidadeOut(Cidade cidade);

    List<CidadeOut> toListCidadeOut(List<Cidade> cidades);

}
