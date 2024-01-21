package br.com.fiap.techFlix.application.useCases;

import br.com.fiap.techFlix.application.gateways.BookmarkVideoGateway;
import br.com.fiap.techFlix.application.gateways.UserGateway;
import br.com.fiap.techFlix.application.gateways.VideoGateway;
import br.com.fiap.techFlix.domain.entities.BookmarkVideo;
import br.com.fiap.techFlix.domain.entities.User;
import br.com.fiap.techFlix.domain.entities.Video;

public class CreateBookmarkVideoUseCase {

    private final BookmarkVideoGateway bookmarkVideoGateway;
    private final UserGateway userGateway;
    private final VideoGateway videoGateway;

    public CreateBookmarkVideoUseCase(BookmarkVideoGateway bookmarkVideoGateway, UserGateway userGateway, VideoGateway videoGateway) {
        this.bookmarkVideoGateway = bookmarkVideoGateway;
        this.userGateway = userGateway;
        this.videoGateway = videoGateway;
    }

    public BookmarkVideo createBookmarkVideo(String videoId, String userId) {
        User user = userGateway.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Video video = videoGateway.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Video not found"));

        return bookmarkVideoGateway.save(new BookmarkVideo(user, video));
    }
}
