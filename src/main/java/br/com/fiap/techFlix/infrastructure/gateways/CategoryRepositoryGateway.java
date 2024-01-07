package br.com.fiap.techFlix.infrastructure.gateways;

import br.com.fiap.techFlix.application.gateways.CategoryGateway;
import br.com.fiap.techFlix.domain.entities.Category;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryDocument;
import br.com.fiap.techFlix.infrastructure.persistence.CategoryRepository;

import java.util.Optional;

public class CategoryRepositoryGateway implements CategoryGateway {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryRepositoryGateway(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryDocument saved = categoryRepository.save(categoryMapper.toPersistence(category));
    }

    @Override
    public Optional<Category> findByName(String name) {
        return Optional.empty();
    }
}
