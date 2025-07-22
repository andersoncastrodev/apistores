package br.com.asoft.apistores.service;

import br.com.asoft.apistores.exceptions.EntityNotFoundExceptions;
import br.com.asoft.apistores.mapper.CategoryMapper;
import br.com.asoft.apistores.model.Category;
import br.com.asoft.apistores.respository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Category saveCategory(Category category) {
        category.setDateRegister(LocalDateTime.now());
       return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category categoryToUpdate = tryOrFail(id);
        categoryMapper.copyToCustomer(category, categoryToUpdate);
        categoryToUpdate.setDateUpdate(LocalDateTime.now());
        return categoryRepository.save(categoryToUpdate);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category tryOrFail(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundExceptions("Category",id));
    }

    public void deleteCategory(Long id) {
        tryOrFail(id); //verificação se a categoria existe
        categoryRepository.deleteById(id);
    }

}
