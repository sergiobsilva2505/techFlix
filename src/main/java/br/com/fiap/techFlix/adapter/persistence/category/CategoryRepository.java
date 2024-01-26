package br.com.fiap.techFlix.adapter.persistence.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository extends MongoRepository<CategoryDocument, String> {

    List<CategoryDocument> findByNameIn(Collection<String> names);

    boolean existsByName(String name);
}
