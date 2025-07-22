package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category copyToCustomer(Category category, @MappingTarget Category categoryToUpdate);
}
