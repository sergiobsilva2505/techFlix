package br.com.fiap.techFlix.adapters.category;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.Optional;

public interface CategoryRepository extends ReactiveCrudRepository<CategoryMapper, String> {
    Optional<CategoryMapper> findByName(String name);
}
