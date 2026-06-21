package br.com.fiap.techflix.application.usecases.category;

import br.com.fiap.techflix.application.gateways.category.CategoryGateway;
import br.com.fiap.techflix.domain.entities.category.Category;

public class ListCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category listCategory(String id) {
        return categoryGateway.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}
