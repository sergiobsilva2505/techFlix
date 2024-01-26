package br.com.fiap.techFlix.application.useCases.bookmark;

import br.com.fiap.techFlix.application.gateways.bookmark.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.gateways.user.UserGateway;
import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.domain.entities.bookmarvideo.BookmarkVideo;
import br.com.fiap.techFlix.domain.entities.user.User;
import br.com.fiap.techFlix.domain.entities.video.Video;

public class CreateBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;
    private final UserGateway userGateway;
    private final VideoGateway videoGateway;

    public CreateBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway, UserGateway userGateway, VideoGateway videoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
        this.userGateway = userGateway;
        this.videoGateway = videoGateway;
    }

    public BookmarkVideo createBookmarkVideo(String userId, String videoId) {
        User user = userGateway.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Video video = videoGateway.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Video not found"));

        if (bookmarkVideoGateway.existsByVideoIdAndUserId(videoId, userId)) {
            throw new IllegalArgumentException("Video already bookmarked");
        }

        videoGateway.likeVideo(videoId);
        return bookmarkVideoGateway.create(user, video);
    }
}
