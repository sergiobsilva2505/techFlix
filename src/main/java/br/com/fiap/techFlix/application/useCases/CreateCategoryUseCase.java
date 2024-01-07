package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.infrastructure.persistence.CategoryDocument;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Mono<CategoryDocument> createCategory(String name) {
        return categoryRepository.save(new CategoryDocument(name));
    }
}
