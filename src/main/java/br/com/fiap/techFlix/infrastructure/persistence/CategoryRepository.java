package br.com.fiap.techFlix.infrastructure.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.Optional;

public interface CategoryRepository extends ReactiveCrudRepository<CategoryDocument, String> {
    Optional<CategoryDocument> findByName(String name);
}
