package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.out.ProdutoOut;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoOut toProdutoOut(Produto produto);

    List<ProdutoOut> toListProdutoOut(List<Produto> produtos);
}
