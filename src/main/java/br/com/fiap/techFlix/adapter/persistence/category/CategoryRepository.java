package br.com.fiap.techFlix.adapter.persistence.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<CategoryDocument, String> {
    Optional<CategoryDocument> findByName(String name);
}
