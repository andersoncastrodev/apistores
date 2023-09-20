package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.out.FornecedorOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    FornecedorOut toFornecedorOut(Fornecedor fornecedor);

    List<FornecedorOut> toListFornecedorOut(List<Fornecedor> fornecedores);


}
