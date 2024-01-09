package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.Category;

public class ListCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category listCategory(String name) {
        return categoryGateway.findByName(name).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}
