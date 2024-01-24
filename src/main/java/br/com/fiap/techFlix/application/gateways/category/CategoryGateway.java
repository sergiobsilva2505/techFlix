package br.com.fiap.techFlix.application.gateways.category;

import br.com.fiap.techFlix.application.ports.CategoryCreatePort;
import br.com.fiap.techFlix.domain.entities.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {

    Category save(CategoryCreatePort categoryCreatePort);

    Optional<Category> findByName(String name);

    List<Category> findAllByNameIn(List<String> strings);
}
