package br.com.fiap.techflix.application.usecases.bookmark;

import br.com.fiap.techflix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techflix.application.gateways.video.VideoGateway;
import br.com.fiap.techflix.domain.entities.bookmarkvideo.BookmarkVideo;

public class DeleteBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;
    private final VideoGateway videoGateway;

    public DeleteBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway, VideoGateway videoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
        this.videoGateway = videoGateway;
    }

    public void deleteBookmarkVideo(String videoId, String userId) {
        BookmarkVideo bookmarkVideo = bookmarkVideoGateway.findByVideoIdAndUserId(videoId, userId).orElseThrow(() -> new IllegalArgumentException("BookmarkVideo not found"));

        videoGateway.unlikeVideo(videoId);
        bookmarkVideoGateway.deleteById(bookmarkVideo.getId());
    }
}
