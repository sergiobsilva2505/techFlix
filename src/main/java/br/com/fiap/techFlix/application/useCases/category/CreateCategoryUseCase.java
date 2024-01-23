package br.com.fiap.techFlix.application.useCases.category;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.ports.CategoryCreatePort;
import br.com.fiap.techFlix.domain.entities.category.Category;

public class CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category createCategory(CategoryCreatePort categoryCreatePort) {
        return categoryGateway.save(categoryCreatePort);
    }
}
