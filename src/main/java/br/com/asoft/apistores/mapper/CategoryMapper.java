package br.com.asoft.apistores.mapper;

import br.com.asoft.apistores.dto.CategoryDto;
import br.com.asoft.apistores.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
    Category copyToCustomer(Category category, @MappingTarget Category categoryToUpdate);
    List<CategoryDto> toListCategoryDto(List<Category> categories);
}
