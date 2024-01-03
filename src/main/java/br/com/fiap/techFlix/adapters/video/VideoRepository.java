package br.com.fiap.techFlix.adapters.video;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VideoRepository extends ReactiveCrudRepository<VideoMapper, String> {
}
