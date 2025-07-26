package br.com.asoft.apistores.controller;

import br.com.asoft.apistores.dto.CategoryDto;
import br.com.asoft.apistores.dto.CategorySimpleDto;
import br.com.asoft.apistores.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/simple")
    public List<CategorySimpleDto> findCategorySimple() {
        return categoryService.findCategorySimple();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
