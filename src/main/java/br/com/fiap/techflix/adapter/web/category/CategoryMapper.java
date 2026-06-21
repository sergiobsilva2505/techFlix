package br.com.fiap.techflix.adapter.web.category;

import br.com.fiap.techflix.adapter.persistence.category.CategoryDocument;
import br.com.fiap.techflix.application.ports.CategoryCreatePort;
import br.com.fiap.techflix.domain.entities.category.Category;

public class CategoryMapper {

    private CategoryMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Category toDomain(CategoryDocument categoryDocument) {
        return new Category(categoryDocument.getId(), categoryDocument.getName());
    }

    public static CategoryDocument toPersistence(Category category) {
        return new CategoryDocument(category.getId(), category.getName());
    }

    public static CategoryDocument toPersistence(CategoryCreatePort categoryCreatePort) {
        return new CategoryDocument(null, categoryCreatePort.name());
    }

    public static CategoryShowDTO toView(Category category) {
        return new CategoryShowDTO(category.getName());
    }
}
