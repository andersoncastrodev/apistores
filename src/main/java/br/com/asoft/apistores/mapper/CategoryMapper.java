package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //Copy os dados do customerDto para o customer
    Category copyToCustomer(Category category, @MappingTarget Category categoryToUpdate);
}
