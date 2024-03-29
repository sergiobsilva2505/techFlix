package br.com.fiap.techFlix.adapter.persistence.category;

import br.com.fiap.techFlix.adapter.web.category.CategoryMapper;
import br.com.fiap.techFlix.application.gateways.category.CategoryGateway;
import br.com.fiap.techFlix.application.ports.CategoryCreatePort;
import br.com.fiap.techFlix.domain.entities.category.Category;

import java.util.List;
import java.util.Optional;

public class CategoryGatewayAdapter implements CategoryGateway {

    private final CategoryRepository categoryRepository;

    public CategoryGatewayAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CategoryCreatePort categoryCreatePort) {
        CategoryDocument saved = categoryRepository.save(CategoryMapper.toPersistence(categoryCreatePort));
        return CategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id).map(CategoryMapper::toDomain);
    }

    @Override
    public List<Category> findAllByNameIn(List<String> strings) {
        return categoryRepository.findByNameIn(strings).stream().map(CategoryMapper::toDomain).toList();
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
