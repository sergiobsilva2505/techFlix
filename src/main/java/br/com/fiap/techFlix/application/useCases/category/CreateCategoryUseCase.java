package br.com.fiap.techFlix.application.useCases.category;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.category.Category;
import br.com.fiap.techFlix.adapter.web.category.CategoryCreateDTO;

public class CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category createCategory(CategoryCreateDTO categoryCreateDTO) {
        return categoryGateway.save(new Category(categoryCreateDTO.name()));
    }
}
