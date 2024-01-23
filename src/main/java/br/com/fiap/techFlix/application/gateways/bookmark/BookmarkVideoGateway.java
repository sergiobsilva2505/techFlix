package br.com.fiap.techFlix.application.gateways.bookmark;

import br.com.fiap.techFlix.application.gateways.PagePort;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;

import java.util.Optional;

public interface BookmarkVideoGateway {

    BookmarkVideo save(BookmarkVideo bookmarkVideo);

    Optional<BookmarkVideo> findByUserId(String userId);

    PagePort<BookmarkVideo> allBookmarkVideo(int page, int size);

    Optional<BookmarkVideo> findByVideoIdAndUserId(String videoId, String userId);

    void deleteById(String id);
}
