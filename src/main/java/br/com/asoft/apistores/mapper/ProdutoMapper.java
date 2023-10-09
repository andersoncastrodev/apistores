package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.ProdutoInp;
import br.com.asoft.apistores.model.Produto;
import br.com.asoft.apistores.out.ProdutoOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoOut toProdutoOut(Produto produto);

    Produto toProduto(ProdutoInp produtoInp);

    Produto copyToProduto(ProdutoInp produtoInp, @MappingTarget Produto produto);

    List<ProdutoOut> toListProdutoOut(List<Produto> produtos);
}
