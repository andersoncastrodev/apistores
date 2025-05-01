package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.inp.ProdutoInp;
import br.com.asoft.apistores.model.Product;
import br.com.asoft.apistores.out.ProdutoOut;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoOut toProdutoOut(Product product);

    Product toProduto(ProdutoInp produtoInp);

    Product copyToProduto(ProdutoInp produtoInp, @MappingTarget Product product);

    List<ProdutoOut> toListProdutoOut(List<Product> products);
}
