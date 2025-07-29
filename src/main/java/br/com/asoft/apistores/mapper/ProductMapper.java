package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.ProductDto;
import br.com.asoft.apistores.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(source = "supplier.id", target = "supplierId") //Para pegar o id do fornecedor
    @Mapping(source = "category.id", target = "categoryId") //Para pegar o id da categoria
    ProductDto toProductDto(Product product);

    @Mapping(target = "supplier", ignore = true)//Para ignorar o supplier (fornecedor)
    @Mapping(target = "category", ignore = true)//Para ignorar a category (categoria)
    Product toProduct(ProductDto productDto);

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product copyToProduct (ProductDto productDto, @MappingTarget Product product);

    List<ProductDto> toListProductDto(List<Product> products);
}
