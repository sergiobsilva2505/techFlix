package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.application.useCases.CreateCategoryUseCase;
import br.com.fiap.techFlix.application.useCases.ListCategoryUseCase;
import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.infrastructure.gateways.CategoryMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final ListCategoryUseCase listCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase, ListCategoryUseCase listCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.listCategoryUseCase = listCategoryUseCase;
    }

    @PostMapping("/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryCreateDTO categoryDTO) {
        Category category = createCategoryUseCase.createCategory(categoryDTO);

        URI uri = URI.create("/categories/" + category.getName());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/categories/{name}")
    public CategoryShowDTO getCategoryByName(@PathVariable String name) {
        return CategoryMapper.toView(listCategoryUseCase.listCategory(name));
    }
}
