package br.com.fiap.techFlix.application.useCases.video;

import br.com.fiap.techFlix.application.gateways.video.VideoGateway;

public class WatchVideoUseCase {

    private final VideoGateway videoGateway;

    public WatchVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public void watchVideo(String id) {
        if (!videoGateway.existsById(id)) {
            throw new IllegalArgumentException("Video not found");
        }

        videoGateway.watchVideo(id);
    }
}
