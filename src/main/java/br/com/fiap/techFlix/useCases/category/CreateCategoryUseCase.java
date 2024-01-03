package br.com.fiap.techFlix.useCases.category;

import br.com.fiap.techFlix.entities.category.Category;

public class CreateCategoryUseCase {

    public Category createCategory(String name) {
        return new Category(name);
    }
}
