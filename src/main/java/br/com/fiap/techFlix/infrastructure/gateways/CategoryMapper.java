package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryDocument;

public class CategoryMapper {

    public static Category toDomain(CategoryDocument categoryDocument) {
        return new Category(categoryDocument.getName());
    }

    public static CategoryDocument toPersistence(Category category) {
        return new CategoryDocument(category.getName());
    }
}
