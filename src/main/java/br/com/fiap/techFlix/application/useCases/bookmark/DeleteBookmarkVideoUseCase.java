package br.com.fiap.techFlix.application.useCases.bookmark;

import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;

public class DeleteBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;

    public DeleteBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
    }

    public void deleteBookmarkVideo(String videoId, String userId) {
        BookmarkVideo bookmarkVideo = bookmarkVideoGateway.findByVideoIdAndUserId(videoId, userId).orElseThrow(() -> new IllegalArgumentException("BookmarkVideo not found"));

        bookmarkVideoGateway.deleteById(bookmarkVideo.getId());
    }
}
