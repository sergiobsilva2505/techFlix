package br.com.fiap.techFlix.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<CategoryDocument, String> {
    Optional<CategoryDocument> findByName(String name);
}
