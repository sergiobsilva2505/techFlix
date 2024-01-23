package br.com.fiap.techFlix.adapter.persistence.bookmarkvideo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookmarkVideoRepository extends MongoRepository<BookmarkVideoDocument, String> {

    Optional<BookmarkVideoDocument> findByVideoIdAndUserId(String videoId, String userId);


}
