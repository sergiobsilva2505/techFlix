package br.com.fiap.techFlix.application.useCases.category;

import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.category.Category;

public class ListCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category listCategory(String id) {
        return categoryGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}
