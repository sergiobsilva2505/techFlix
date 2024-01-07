package br.com.fiap.techFlix.infrastructure.controllers;

import br.com.fiap.techFlix.infrastructure.persistence.CategoryDocument;
import br.com.fiap.techFlix.application.useCases.CreateCategoryUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @PostMapping("/categories")
    public Mono<String> createCategory(@Valid @RequestBody CategoryCreateDTO categoryDTO) {
        return createCategoryUseCase.createCategory("name").map(CategoryDocument::getId).map(id -> "/categories/" + id);
    }
}
