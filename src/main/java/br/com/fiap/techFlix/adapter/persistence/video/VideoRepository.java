package br.com.fiap.techFlix.adapter.persistence.video;

import org.springframework.data.mongodb.repository.*;

public interface VideoRepository extends MongoRepository<VideoDocument, String> {

    @Query("{ '_id': ?0 }")
    @Update("{ $inc: { 'details.views': 1 } }")
    void addView(String id);
}
