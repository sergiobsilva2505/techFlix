package br.com.fiap.techFlix.adapter.persistence.video;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<VideoDocument, String> {
}
