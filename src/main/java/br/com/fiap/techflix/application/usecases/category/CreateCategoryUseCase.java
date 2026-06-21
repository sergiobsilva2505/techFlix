package br.com.fiap.techflix.application.usecases.category;

import br.com.fiap.techflix.application.gateways.category.CategoryGateway;
import br.com.fiap.techflix.application.ports.CategoryCreatePort;
import br.com.fiap.techflix.domain.entities.category.Category;

public class CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category createCategory(CategoryCreatePort categoryCreatePort) {
        if (categoryGateway.existsByName(categoryCreatePort.name())) {
            throw new IllegalArgumentException("Category already exists");
        }

        return categoryGateway.save(categoryCreatePort);
    }
}
