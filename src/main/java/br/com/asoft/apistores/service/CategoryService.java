package br.com.asoft.apistores.service;

import br.com.asoft.apistores.dto.CategoryDto;
import br.com.asoft.apistores.dto.CategorySimpleDto;
import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.mapper.CategoryMapper;
import br.com.asoft.apistores.model.Category;
import br.com.asoft.apistores.respository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        category.setDateRegister(LocalDateTime.now());
       return categoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        Category categoryToUpdate = tryOrFail(id);
        categoryMapper.copyToCustomer(category, categoryToUpdate);
        categoryToUpdate.setDateUpdate(LocalDateTime.now());
        return categoryMapper.toCategoryDto( categoryRepository.save(categoryToUpdate));
    }

    public List<CategoryDto> findAll() {
        return categoryMapper.toListCategoryDto( categoryRepository.findAll());
    }

    public List<CategorySimpleDto> findCategorySimple() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategorySimpleDto(category.getId(), category.getDescription()))
                .collect(Collectors.toList());
    }

    public Category tryOrFail(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Category",id));
    }

    @Transactional
    public void deleteCategory(Long id) {
        tryOrFail(id); //verificação se a categoria existe
        categoryRepository.deleteById(id);
        categoryRepository.flush();
    }

}
