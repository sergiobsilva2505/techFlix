package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.BookmarkVideoGateway;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;

public class DeleteBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;

    public DeleteBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
    }

    public void deleteBookmarkVideo(String videoId, String userId) {
        BookmarkVideo bookmarkVideo = bookmarkVideoGateway.findByVideoIdAndUserId(userId, videoId).orElseThrow(() -> new IllegalArgumentException("BookmarkVideo not found"));

        bookmarkVideoGateway.deleteById(bookmarkVideo.getId());
    }
}
