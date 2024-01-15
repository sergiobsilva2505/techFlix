package br.com.fiap.techFlix.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {
}
