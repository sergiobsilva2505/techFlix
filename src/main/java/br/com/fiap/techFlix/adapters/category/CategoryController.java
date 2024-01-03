package br.com.fiap.techFlix.adapters.category;

import br.com.fiap.techFlix.useCases.category.CreateCategoryUseCase;
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
        return createCategoryUseCase.createCategory("name").map(CategoryMapper::getId).map(id -> "/categories/" + id);
    }
}
