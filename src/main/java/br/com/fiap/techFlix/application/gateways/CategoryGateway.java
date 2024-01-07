package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.Category;

import java.util.Optional;

public interface CategoryGateway {

    Category save(Category category);
    Optional<Category> findByName(String name);
}
