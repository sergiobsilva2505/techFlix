package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryDocument;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryRepository;

import java.util.Optional;

public class CategoryRepositoryGateway implements CategoryGateway {

    private final CategoryRepository categoryRepository;

    public CategoryRepositoryGateway(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryDocument saved = categoryRepository.save(CategoryMapper.toPersistence(category));
        return CategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name).map(CategoryMapper::toDomain);
    }
}
