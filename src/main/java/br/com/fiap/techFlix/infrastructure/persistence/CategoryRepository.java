package br.com.fiap.techFlix.infrastructure.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface CategoryRepository extends ReactiveCrudRepository<CategoryDocument, String> {
    Mono<CategoryDocument> findByName(String name);
}
