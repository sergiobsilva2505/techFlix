package br.com.fiap.techFlix.application.gateways.bookmark;

import br.com.fiap.techFlix.application.ports.PagePort;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;

import java.util.Optional;

public interface BookmarkVideoGateway {

    BookmarkVideo create(User user, Video video);

    Optional<BookmarkVideo> findByUserId(String userId);

    PagePort<BookmarkVideo> allBookmarkVideo(int page, int size);

    Optional<BookmarkVideo> findByVideoIdAndUserId(String videoId, String userId);

    boolean existsByVideoIdAndUserId(String videoId, String userId);

    void deleteById(String id);
}
