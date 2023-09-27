package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.CidadeInp;
import br.com.asoft.apistores.model.Cidade;
import br.com.asoft.apistores.out.CidadeOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CidadeMapper {

    CidadeOut toCidadeOut(Cidade cidade);

    Cidade toCidade(CidadeInp cidadeInp);
    List<CidadeOut> toListCidadeOut(List<Cidade> cidades);

}
