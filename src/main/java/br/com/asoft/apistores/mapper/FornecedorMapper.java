package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.FornecedorInp;
import br.com.asoft.apistores.model.Fornecedor;
import br.com.asoft.apistores.out.FornecedorOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    FornecedorOut toFornecedorOut(Fornecedor fornecedor);

    Fornecedor toFornecedor(FornecedorInp fornecedorInp);

    Fornecedor copyToFornecedor(FornecedorInp fornecedorInp, @MappingTarget Fornecedor fornecedor);

    List<FornecedorOut> toListFornecedorOut(List<Fornecedor> fornecedores);


}
