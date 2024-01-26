package br.com.fiap.techFlix.adapter.web.category;

import br.com.fiap.techFlix.application.useCases.category.CreateCategoryUseCase;
import br.com.fiap.techFlix.application.useCases.category.ListCategoryUseCase;
import br.com.fiap.techFlix.domain.entities.category.Category;
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

        URI uri = URI.create("/categories/" + category.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/categories/{id}")
    public CategoryShowDTO getCategoryById(@PathVariable String id) {
        return CategoryMapper.toView(listCategoryUseCase.listCategory(id));
    }
}
