package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;
import br.com.fiap.techFlix.application.ports.UserBookmarkedCategoriesPort;
import br.com.fiap.techFlix.domain.entities.video.Video;

import java.util.List;

public class VideoRecommendationsUseCase {

    private final VideoGateway videoGateway;

    public VideoRecommendationsUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public List<Video> getRecommendations(String userId) {
        List<UserBookmarkedCategoriesPort> categories = videoGateway.getLikedCategories(userId);

        if (categories.isEmpty()) {
            return videoGateway.getRecommendations();
        }

        return videoGateway.getRecommendations(categories);
    }
}
