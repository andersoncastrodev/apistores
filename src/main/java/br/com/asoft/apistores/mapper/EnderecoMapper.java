package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Endereco;
import br.com.asoft.apistores.out.EnderecoOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoOut toEnderecoOut(Endereco endereco);
    List<EnderecoOut> toListEnderecoOut(List<Endereco> enderecos);
}
