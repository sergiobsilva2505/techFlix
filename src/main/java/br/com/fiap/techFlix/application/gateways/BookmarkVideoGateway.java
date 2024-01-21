package br.com.fiap.techFlix.application.gateways;

import br.com.fiap.techFlix.domain.entities.BookmarkVideo;

import java.util.List;
import java.util.Optional;

public interface BookmarkVideoGateway {

    BookmarkVideo save(BookmarkVideo bookmarkVideo);

    Optional<BookmarkVideo> findByUserId(String userId);

    List<BookmarkVideo> allBookmarkVideo();

    Optional<BookmarkVideo> findByVideoIdAndUserId(String videoId, String userId);

    void deleteById(String id);
}
