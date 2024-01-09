package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.infrastructure.controllers.CategoryCreateDTO;

public class CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category createCategory(CategoryCreateDTO categoryCreateDTO) {
        return categoryGateway.save(new Category(categoryCreateDTO.name()));
    }
}
