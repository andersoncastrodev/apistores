package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.EnderecoIdInp;
import br.com.asoft.apistores.inp.EnderecoInp;
import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.out.EnderecoOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoOut toEnderecoOut(Endereco endereco);

    Endereco toEndereco(EnderecoInp enderecoInp);

    Endereco copyToEndereco(EnderecoInp enderecoInp, @MappingTarget Endereco endereco);

    List<EnderecoOut> toListEnderecoOut(List<Endereco> enderecos);
}
