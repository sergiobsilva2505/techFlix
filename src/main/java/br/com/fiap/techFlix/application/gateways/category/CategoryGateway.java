package br.com.fiap.techFlix.application.gateways.category;

import br.com.fiap.techFlix.domain.entities.category.Category;

import java.util.Optional;

public interface CategoryGateway {

    Category save(Category category);
    Optional<Category> findByName(String name);
}
