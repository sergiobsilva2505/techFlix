package br.com.fiap.techflix.application.gateways.bookmark;

import br.com.fiap.techflix.application.ports.PagePort;
import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;
import br.com.fiap.techflix.domain.entities.user.User;
import br.com.fiap.techflix.domain.entities.video.Video;

import java.util.Optional;

public interface BookmarkVideoGateway {

    BookmarkVideo create(User user, Video video);

    Optional<BookmarkVideo> findById(String bookmarkId);

    PagePort<BookmarkVideo> allBookmarkVideo(int page, int size);

    Optional<BookmarkVideo> findByVideoIdAndUserId(String videoId, String userId);

    boolean existsByVideoIdAndUserId(String videoId, String userId);

    void deleteById(String id);
}
