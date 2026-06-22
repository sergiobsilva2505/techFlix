package br.com.fiap.techflix.adapter.persistence.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    boolean existsByEmail(String email);
}
