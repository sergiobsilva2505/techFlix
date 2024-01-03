package br.com.fiap.techFlix.useCases.category;

import br.com.fiap.techFlix.adapters.category.CategoryMapper;
import br.com.fiap.techFlix.adapters.category.CategoryRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Mono<CategoryMapper> createCategory(String name) {
        return categoryRepository.save(new CategoryMapper(name));
    }
}
