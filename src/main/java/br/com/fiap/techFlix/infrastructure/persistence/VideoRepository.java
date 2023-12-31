package br.com.fiap.techFlix.infrastructure.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VideoRepository extends ReactiveCrudRepository<VideoDocument, String> {
}
